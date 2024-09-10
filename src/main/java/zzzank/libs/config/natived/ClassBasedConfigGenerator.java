package zzzank.libs.config.natived;

import lombok.val;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.FieldWrapper;
import net.minecraftforge.fml.common.Loader;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.api.entry.ConfigEntry;
import zzzank.libs.config.api.entry.ConfigRoot;
import zzzank.libs.config.impl.builder.ConfigAttributeBuilder;
import zzzank.libs.config.impl.builder.ConfigCategoryBuilder;
import zzzank.libs.config.impl.entry.DefaultConfigRoot;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * @author ZZZank
 */
public class ClassBasedConfigGenerator {

    public static ConfigRoot scan(Class<?> target) {
        val cfgAnno = target.getAnnotation(Config.class);
        if (cfgAnno == null) {
            throw new IllegalArgumentException("not a config class");
        }
        val attributeBuilder = computeAttribute(target);
        if (attributeBuilder.displayName == null) {
            attributeBuilder.displayName = cfgAnno.category(); //todo: what should it be
        }
        val configName = cfgAnno.name().isEmpty() ? cfgAnno.modid() : cfgAnno.name();
        val root = DefaultConfigRoot.builder()
            .setName(cfgAnno.category())
            .setIdentifier(target)
            .setAttribute(attributeBuilder.build())
            .setPath(Loader.instance().getConfigDir().toPath().resolve(configName))
            .setFormat(null) //todo: FORMAT
            .build();
        //first level config always uses static member, so instance is null
        scanClazz(root, target, null);
        return root;
    }

    private static void scanClazz(ConfigCategory category, Class<?> target, Object instance) {
        if (!target.isAnnotationPresent(Config.class)) {
            return;
        }
        for (var field : target.getDeclaredFields()) {
            var modifier = field.getModifiers();
            if (
                !Modifier.isPublic(modifier)
                || Modifier.isStatic(modifier) != Objects.isNull(instance)
                || Modifier.isFinal(modifier)
                || field.isAnnotationPresent(Config.Ignore.class)
            ) {
                continue;
            }
            val entry = scanField(category, field, instance);
            category.get().put(entry.getName(), entry);
        }
    }

    private static ConfigEntry<?> scanField(ConfigCategory parent, Field field, Object source) {
        field.setAccessible(true);
        //determine attribute from annotations
        val attributeBuilder = computeAttribute(field);
        if (attributeBuilder.displayName == null) {
            attributeBuilder.displayName = field.getName();
        }
        //determine config type: normal entry or category?
        if (FieldWrapper.hasWrapperFor(field)) {
            //primitive / map / enum -> entry
            //todo: type adapter
            try {
                val staticValue = field.get(source);
                val type = field.getType();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return null;
        } else if (ConfigEntry.class.isAssignableFrom(field.getType())) {
            //entry -> inject, attach auto-save listener if required

        } else if (Objects.equals(field.getType().getSuperclass(), Object.class)) {
            //class -> category
            val subCategory = ConfigCategoryBuilder.of()
                .setName(field.getName())
                .setAttribute(attributeBuilder.build())
                .setParent(parent)
                .build();
            try {
                scanClazz(subCategory, field.getType(), field.get(source));
            } catch (IllegalAccessException ignored) {
                //todo: error logging
            }
            return subCategory;
        }
        throw new RuntimeException(String.format(
            "Can't handle field '%s' of class '%s': Unknown type.",
            field.getName(),
            field.getDeclaringClass().getCanonicalName()
        ));
    }

    public static ConfigAttributeBuilder computeAttribute(AnnotatedElement annotated) {
        return ConfigAttribute
            .builder()
            .setRequiresMcRestart(annotated.isAnnotationPresent(Config.RequiresMcRestart.class))
            .setRequiresWorldRestart(annotated.isAnnotationPresent(Config.RequiresWorldRestart.class))
            .setHasSlidingControl(annotated.isAnnotationPresent(Config.SlidingOption.class))
            .setDisplayName(annotated.isAnnotationPresent(Config.Name.class)
                ? annotated.getAnnotation(Config.Name.class).value()
                : null
            )
            .setLangLey(annotated.isAnnotationPresent(Config.LangKey.class)
                ? annotated.getAnnotation(Config.LangKey.class).value()
                : null
            )
            .setComments(annotated.isAnnotationPresent(Config.Comment.class)
                ? annotated.getAnnotation(Config.Comment.class).value()
                : null
            );
    }

    public static ConfigBound<?> computeConfigBound(Field field) {
        val doubleRange = field.getAnnotation(Config.RangeDouble.class);
        val intRange = field.getAnnotation(Config.RangeInt.class);
        if (doubleRange != null && intRange != null) {
            throw new IllegalStateException(""); //todo
        }

        return null;
    }
}
