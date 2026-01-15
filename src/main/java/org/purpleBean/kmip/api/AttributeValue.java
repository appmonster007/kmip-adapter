package org.purpleBean.kmip.api;

import org.purpleBean.kmip.model.core.structure.Attribute;

/**
 * Represents the value of a KMIP attribute.
 * <p>
 * This interface extends {@link KmipDataType} and serves as a marker for all data types
 * that can be used as the value of a KMIP attribute. In the KMIP specification, attributes
 * are contained within an {@code Attribute} structure, which consists of an
 * {@code AttributeName} and an {@code AttributeValue}.
 *
 * <p>This interface unifies the various possible data types for an attribute's value,
 * such as integers, enumerations, text strings, and structures, under a common type.
 * The actual data and its {@link EncodingType} are provided by the specific
 * implementation of this interface.</p>
 *
 * <p><b>Key Characteristics:</b></p>
 * <ul>
 *   <li><b>KMIP Tag:</b> All attribute values are associated with the standard
 *       {@code AttributeValue} tag from the KMIP specification.</li>
 *   <li><b>Polymorphic Nature:</b> Implementations of this interface can represent
 *       any valid KMIP data type, allowing for a generic and flexible attribute
 *       handling mechanism.</li>
 * </ul>
 *
 * @see KmipDataType
 * @see KmipAttribute
 * @see Attribute
 */
public interface AttributeValue extends KmipDataType {
    /**
     * The standard KMIP tag for an Attribute Value, which is always {@link KmipTag.Standard#ATTRIBUTE_VALUE}.
     */
    KmipTag kmipTag = KmipTag.Standard.ATTRIBUTE_VALUE.inst();
}
