package org.purpleBean.kmip.api;

import org.purpleBean.kmip.model.core.enumeration.ObjectType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents the Managed Object data type in KMIP.
 * <p>
 * This interface extends {@link KmipDataType} and defines the contract for all KMIP Managed
 * Objects. Managed Objects are the primary entities managed by the KMIP server, such as
 * Symmetric Keys, Public Keys, Private Keys, Certificates, etc.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>KMIP Tag:</b> Defines the standard KMIP tag for Managed Object.</li>
 *   <li><b>Dynamic Registration:</b> Provides a registry system to map specific {@link ObjectType}
 *       values and {@link EncodingType}s to their corresponding Java class implementations and
 *       builder functions. This allows the codec to dynamically handle different managed object
 *       types during serialization and deserialization.</li>
 * </ul>
 *
 * @see KmipDataType
 * @see ObjectType
 */
public interface ManagedObject extends KmipDataType {

    /**
     * A registry mapping a unique key (KMIP spec, encoding type, object type) to the
     * specific {@link KmipDataType} class that represents that managed object type.
     */
    Map<RegistryKey, Class<? extends KmipDataType>> OBJECT_TYPE_REGISTRY = new ConcurrentHashMap<>();

    /**
     * A registry mapping a unique key (KMIP spec, encoding type, object type) to a
     * builder function that can construct a specific {@link ManagedObject} instance.
     */
    Map<RegistryKey, Function<List<KmipDataType>, ? extends ManagedObject>> OBJECT_TYPE_BUILDER_REGISTRY = new ConcurrentHashMap<>();

    /**
     * Registers a {@link ManagedObject} class and its builder function with the central registries.
     * <p>
     * This method should be called for each supported managed object type to enable dynamic
     * handling by the codec.
     *
     * @param spec              The {@link KmipSpec} version for which this mapping is valid.
     * @param encodingType      The {@link EncodingType} of the managed object.
     * @param objectTypeValue   The {@link ObjectType.Value} that specifies the type of the managed object.
     * @param clazz             The {@link Class} that implements the specific managed object type.
     * @param objectTypeBuilder A {@link Function} that constructs an instance of the specific
     *                          managed object type from a generic {@link ManagedObject} object.
     */
    static void register(
            KmipSpec spec,
            EncodingType encodingType,
            ObjectType.Value objectTypeValue,
            Class<? extends KmipDataType> clazz,
            Function<List<KmipDataType>, ? extends ManagedObject> objectTypeBuilder
    ) {
        OBJECT_TYPE_REGISTRY.put(new RegistryKey(spec, encodingType, objectTypeValue), clazz);
        OBJECT_TYPE_BUILDER_REGISTRY.put(new RegistryKey(spec, encodingType, objectTypeValue), objectTypeBuilder);
    }

    /**
     * Retrieves the corresponding {@link KmipDataType} class from the registry based on
     * the KMIP specification, encoding type, and object type.
     *
     * @param encodingType    The {@link EncodingType} of the managed object.
     * @param objectTypeValue The {@link ObjectType.Value} of the managed object.
     * @return The registered {@link Class}, or {@code null} if no mapping is found.
     */
    static Class<? extends KmipDataType> getClassFromRegistry(EncodingType encodingType, ObjectType.Value objectTypeValue) {
        KmipSpec spec = KmipContext.getSpec();
        return OBJECT_TYPE_REGISTRY.get(new RegistryKey(spec, encodingType, objectTypeValue));
    }

    /**
     * Retrieves the builder function for a specific managed object type from the registry.
     *
     * @param encodingType    The {@link EncodingType} of the managed object.
     * @param objectTypeValue The {@link ObjectType.Value} of the managed object.
     * @return The registered {@link Function} builder, or {@code null} if no mapping is found.
     */
    static Function<List<KmipDataType>, ? extends ManagedObject> getBuilderFromRegistry(EncodingType encodingType, ObjectType.Value objectTypeValue) {
        KmipSpec spec = KmipContext.getSpec();
        return OBJECT_TYPE_BUILDER_REGISTRY.get(new RegistryKey(spec, encodingType, objectTypeValue));
    }

    /**
     * Returns the {@link ObjectType} of this managed object.
     *
     * @return the object type.
     */
    ObjectType getObjectType();

    /**
     * A composite key for the managed object registries, uniquely identifying a managed object
     * by its specification, encoding type, and object type.
     *
     * @param spec            The KMIP specification version.
     * @param encodingType    The encoding type of the managed object.
     * @param objectTypeValue The type of the managed object.
     */
    record RegistryKey(KmipSpec spec, EncodingType encodingType, ObjectType.Value objectTypeValue) {
    }
}
