package org.purpleBean.kmip.api;

/**
 * Represents a KMIP (Key Management Interoperability Protocol) Enumeration data type.
 * <p>
 * This interface extends {@link KmipDataType} and serves as the base for all KMIP
 * enumerations. An enumeration in KMIP is a set of named integer constants that
 * represent specific values for a given attribute or parameter (e.g., Result Status,
 * Cryptographic Algorithm).
 *
 * <p><b>Key Characteristics:</b></p>
 * <ul>
 *   <li><b>Fixed Encoding:</b> All KMIP enumerations have a predefined {@link EncodingType}
 *       of {@code ENUMERATION}.</li>
 *   <li><b>Integer Value:</b> Each enumeration constant has an underlying integer value
 *       that is used in the TTLV encoding.</li>
 *   <li><b>Descriptive Name:</b> Each enumeration constant also has a human-readable
 *       description or name.</li>
 * </ul>
 *
 * <p>Implementations of this interface will provide the specific set of constants for a
 * particular KMIP enumeration, along with methods to access their values and descriptions.</p>
 *
 * @see KmipDataType
 * @see EncodingType
 */
public interface KmipEnumeration extends KmipDataType {

    /**
     * The fixed encoding type for all KMIP Enumerations, which is always {@link EncodingType#ENUMERATION}.
     */
    EncodingType encodingType = EncodingType.ENUMERATION;

    /**
     * Gets the integer value of the enumeration constant.
     * <p>
     * This is the value that is used in the TTLV encoding of the KMIP message.
     *
     * @return The integer value of the enumeration.
     */
    int getValue();

    /**
     * Gets the human-readable description of the enumeration constant.
     *
     * @return The string description of the enumeration.
     */
    String getDescription();
}
