package org.purpleBean.kmip.api;

/**
 * Represents a vendor-specific extension to the KMIP (Key Management Interoperability Protocol).
 * <p>
 * This interface serves as a marker for any data type that is not part of the standard
 * KMIP specification but is instead defined by a specific vendor. Implementing this
 * interface allows custom, vendor-defined data types to be integrated into the KMIP
 * framework alongside standard types.
 *
 * <p><b>Key Characteristics:</b></p>
 * <ul>
 *   <li><b>Marker Interface:</b> It does not declare any methods of its own but inherits
 *       from {@link KmipDataType}. Its primary purpose is to identify a class as a
 *       vendor extension.</li>
 *   <li><b>Custom Tags:</b> Vendor extensions are typically associated with custom
 *       (non-standard) {@link KmipTag} values, which should be in the range reserved
 *       for vendor-specific use (e.g., {@code 0x540000} to {@code 0x54FFFF}).</li>
 *   <li><b>Integration:</b> By implementing this interface and registering with the
 *       {@link KmipDataType#TAG_REGISTRY}, vendor-defined types can be seamlessly
 *       processed by the codec during serialization and deserialization.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * To create a custom vendor extension, a class should implement this interface, define
 * its structure and behavior, and register itself using the mechanisms provided in
 * {@link KmipDataType}.
 *
 * <pre>
 * {@code
 * // Example of a custom vendor-defined structure
 * public class MyCustomStructure implements VendorExtension, KmipStructure {
 *     // ... implementation details ...
 *
 *     static {
 *         // Register this custom type for a specific KMIP version and tag
 *         KmipDataType.register(
 *             KmipSpec.V1_2,
 *             MyCustomTag.MY_CUSTOM_STRUCTURE.inst().getValue(),
 *             EncodingType.STRUCTURE,
 *             MyCustomStructure.class
 *         );
 *     }
 * }
 * }
 * </pre>
 *
 * @see KmipDataType
 * @see KmipTag
 */
public interface VendorExtension extends KmipDataType {
}
