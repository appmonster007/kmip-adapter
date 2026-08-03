package org.purplebean.kmip.api;

/**
 * Marker interface for KMIP {@code Data} values.
 * <p>
 * KMIP §11.13 defines the {@code Data} tag ({@code 0x4200C2}) as polymorphic across encodings:
 * <ul>
 *   <li><b>ByteString</b> — literal bytes of data (see
 *       {@link org.purplebean.kmip.model.core.type.DataByteString}).</li>
 *   <li><b>Enumeration</b> — a placeholder reference to another batch item's data output,
 *       e.g. {@code <Data type="Enumeration" value="Encrypt"/>} in streaming batches
 *       (see {@link org.purplebean.kmip.model.v2x1.enumeration.DataEnumeration}). This form
 *       is introduced in KMIP 2.1 for Encrypt/Decrypt/Hash/MAC/RNG-Retrieve/Sign/SignatureVerify
 *       payloads that consume the output of a prior batch item.</li>
 * </ul>
 * <p>
 * Structurally mirrors {@link KeyValue} — both are single-tag polymorphic containers whose
 * concrete implementations differ by {@link EncodingType}. Payload fields typed as {@code
 * DataValue}
 * should be deserialized via polymorphic {@code KmipDataType.class} dispatch (see
 * {@code KmipDataTypeXmlDeserializer}) and cast to this interface.
 *
 * <p><b>Naming note:</b> called {@code DataValue} (not {@code Data}) to avoid clashing with
 * {@link lombok.Data} in classes that use wildcard imports on this package.
 *
 * @see KmipDataType
 * @see org.purplebean.kmip.model.core.type.DataByteString
 * @see org.purplebean.kmip.model.v2x1.enumeration.DataEnumeration
 */
public interface DataValue extends KmipDataType {
  /**
   * The standard KMIP tag for a Data value, always {@link KmipTag.Standard#DATA}.
   */
  KmipTag kmipTag = KmipTag.Standard.DATA.inst();
}
