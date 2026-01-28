package org.purpleBean.kmip.api;

import java.util.List;

/**
 * Represents a KMIP Structure data type.
 * <p>
 * This interface extends {@link KmipDataType} and serves as a marker for all KMIP
 * data types that are structures. A KMIP Structure is a composite data type that
 * contains an ordered sequence of other {@link KmipDataType} instances.
 *
 * <p><b>Key Characteristics:</b></p>
 * <ul>
 *   <li><b>Fixed Encoding:</b> All KMIP Structures have a predefined {@link EncodingType}
 *       of {@code STRUCTURE}.</li>
 *   <li><b>Composite Nature:</b> A structure is composed of a list of other KMIP
 *       data types, which can be simple values, enumerations, or other structures.</li>
 * </ul>
 *
 * <p>Implementations of this interface are responsible for managing the list of
 * contained KMIP data types and providing them for serialization and inspection.</p>
 *
 * @see KmipDataType
 * @see EncodingType
 */
public interface KmipStructure extends KmipDataType {

    /**
     * The fixed encoding type for all KMIP Structures, which is always {@link EncodingType#STRUCTURE}.
     */
    EncodingType encodingType = EncodingType.STRUCTURE;

    /**
     * Retrieves the ordered list of {@link KmipDataType} instances that make up this structure.
     * <p>
     * The order of the elements in the list is significant and corresponds to their
     * sequence within the KMIP structure as defined by the specification.
     *
     * @return A {@link List} of {@link KmipDataType} objects contained within this structure.
     */
    KmipDataType[] getValue();
}
