package org.purpleBean.kmip.test.suite;

import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipEnumeration;

/**
 * Provides a comprehensive test suite for KMIP data types that function as both an enumeration and an attribute.
 *
 * @deprecated Use {@link AbstractKmipEnumerationTestSuite} and implement {@link KmipAttributeTestSuite}.
 */
@Deprecated
public abstract class AbstractKmipEnumerationAttributeTestSuite<T extends KmipEnumeration & KmipAttribute>
        extends AbstractKmipEnumerationTestSuite<T> implements KmipAttributeTestSuite<T> {
}
