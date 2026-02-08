package org.purpleBean.kmip.test.suite;

import org.purpleBean.kmip.api.KmipAttribute;

/**
 * Base domain suite for objects implementing KmipAttribute.
 * Implementors provide expectations and representative states where required.
 *
 * @deprecated Use {@link AbstractKmipDataTypeTestSuite} and implement {@link KmipAttributeTestSuite}.
 */
@Deprecated
public abstract class AbstractKmipDataTypeAttributeTestSuite<T extends KmipAttribute>
        extends AbstractKmipDataTypeTestSuite<T> implements KmipAttributeTestSuite<T> {
}
