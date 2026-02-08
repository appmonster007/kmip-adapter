package org.purpleBean.kmip.test.suite;

import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipStructure;

/**
 * Provides a comprehensive test suite for KMIP data types that function as both a structure and an attribute.
 *
 * @deprecated Use {@link AbstractKmipStructureTestSuite} and implement {@link KmipAttributeTestSuite}.
 */
@Deprecated
public abstract class AbstractKmipStructureAttributeTestSuite<T extends KmipStructure & KmipAttribute>
        extends AbstractKmipStructureTestSuite<T> implements KmipAttributeTestSuite<T> {
}
