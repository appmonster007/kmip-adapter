package org.purplebean.kmip.api;

import org.purplebean.kmip.model.core.enumeration.KeyFormatType;

/**
 * Represents the Key Value data type in KMIP.
 *
 * <p>This interface extends {@link KmipDataType} and serves as a marker for all KMIP
 * Key Value objects. A Key Value structure contains the {@link KeyMaterial} of a
 * cryptographic key. It acts as a container for the actual key data, which can be
 * in various formats as specified by the {@link KeyFormatType}.
 *
 * <p><b>Key Characteristics:</b></p>
 * <ul>
 *   <li><b>KMIP Tag:</b> All Key Value objects are associated with the standard
 *       {@code KeyValue} tag from the KMIP specification.</li>
 *   <li><b>Container Role:</b> This structure's primary purpose is to hold the
 *       {@link KeyMaterial}, which contains the actual bytes of the key.</li>
 * </ul>
 *
 * @see KmipDataType
 * @see KeyMaterial
 * @see org.purplebean.kmip.model.core.structure.KeyBlock
 */
public interface KeyValue extends KmipDataType {
  /**
   * The standard KMIP tag for a Key Value, which is always {@link KmipTag.Standard#KEY_VALUE}.
   */
  KmipTag kmipTag = KmipTag.Standard.KEY_VALUE.inst();
}
