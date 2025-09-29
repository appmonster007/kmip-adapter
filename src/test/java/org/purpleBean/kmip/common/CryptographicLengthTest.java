package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeAttributeSuite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("CryptographicLength Domain Tests")
class CryptographicLengthTest extends AbstractKmipDataTypeAttributeSuite<CryptographicLength> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CryptographicLength> type() {
        return CryptographicLength.class;
    }

    @Override
    protected CryptographicLength createDefault() {
        return CryptographicLength.of(256);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }

    @Test
    @DisplayName("should create with valid bit length")
    void shouldCreateWithValidBitLength() {
        CryptographicLength length = CryptographicLength.of(128);
        assertThat(length.getValue()).isEqualTo(128);
    }

    @Override
    protected boolean expectAlwaysPresent() {
        return false;
    }

    @Override
    protected boolean expectServerInitializable() {
        return false;
    }

    @Override
    protected boolean expectClientInitializable() {
        return true;
    }

    @Override
    protected boolean expectClientDeletable() {
        return false;
    }

    @Override
    protected boolean expectMultiInstanceAllowed() {
        return false;
    }

    @Override
    protected State stateForServerModifiableTrue() {
        // Return a state where server modification is allowed
        return new State(State.Standard.PRE_ACTIVE);
    }

    @Override
    protected State stateForServerModifiableFalse() {
        // Return a state where server modification is not allowed
        return new State(State.Standard.ACTIVE);
    }

    @Override
    protected State stateForClientModifiableTrue() {
        // Return a state where client modification is allowed
        return new State(State.Standard.PRE_ACTIVE);
    }

    @Override
    protected State stateForClientModifiableFalse() {
        // Return a state where client modification is not allowed
        return new State(State.Standard.ACTIVE);
    }

    @Test
    @DisplayName("should create from attribute value")
    void shouldCreateFromAttributeValue() {
        AttributeValue attrValue = AttributeValue.of(192);
        CryptographicLength length = CryptographicLength.fromValue(attrValue);
        assertThat(length.getValue()).isEqualTo(192);
    }

    @Test
    @DisplayName("should throw for invalid attribute value type")
    void shouldThrowForInvalidAttributeValueType() {
        AttributeValue invalidAttrValue = AttributeValue.of("invalid");
        assertThatThrownBy(() -> CryptographicLength.fromValue(invalidAttrValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid attribute value for CryptographicLength");
    }

    @Test
    @DisplayName("should have correct attribute properties")
    void shouldHaveCorrectAttributeProperties() {
        CryptographicLength length = createDefault();

        assertThat(length.getAttributeName().getValue()).isEqualTo("Cryptographic Length");
        assertThat(length.getCanonicalName()).isEqualTo("CryptographicLength");
        assertThat(length.isAlwaysPresent()).isFalse();
        assertThat(length.isClientInitializable()).isTrue();
        assertThat(length.isServerInitializable()).isFalse();
        assertThat(length.isClientDeletable()).isFalse();

        // Test with a sample state - should not be modifiable in any state
        State sampleState = new State(State.Standard.ACTIVE);
        assertThat(length.isServerModifiable(sampleState)).isFalse();
        assertThat(length.isClientModifiable(sampleState)).isFalse();
    }

    @Test
    @DisplayName("should support KMIP specs")
    void shouldSupportKmipSpecs() {
        CryptographicLength length = createDefault();
        assertThat(length.isSupportedFor(KmipSpec.V1_2)).isTrue();
        assertThat(length.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        // Should not support unsupported versions
        assertThat(length.isSupportedFor(KmipSpec.V1_0)).isFalse();
        // Should not throw NPE for null spec
        assertThatThrownBy(() -> length.isSupportedFor(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("KMIP spec cannot be null");
    }

    @Test
    @DisplayName("should have correct encoding type")
    void shouldHaveCorrectEncodingType() {
        CryptographicLength length = createDefault();
        assertThat(length.getEncodingType()).isEqualTo(EncodingType.INTEGER);
    }

    @Test
    @DisplayName("should have correct KMIP tag")
    void shouldHaveCorrectKmipTag() {
        CryptographicLength length = createDefault();
        assertThat(length.getKmipTag().getDescription()).isEqualTo("CryptographicLength");
    }

    @Test
    @DisplayName("should have correct attribute value")
    void shouldHaveCorrectAttributeValue() {
        CryptographicLength length = CryptographicLength.of(512);
        AttributeValue attrValue = length.getAttributeValue();

        assertThat(attrValue.getEncodingType()).isEqualTo(EncodingType.INTEGER);
        assertThat(attrValue.getValue()).isEqualTo(512);
    }

    @Test
    @DisplayName("should implement equals and hashCode")
    void shouldImplementEqualsAndHashCode() {
        CryptographicLength length1 = CryptographicLength.of(128);
        CryptographicLength length2 = CryptographicLength.of(128);
        CryptographicLength different = CryptographicLength.of(256);

        // Test equals
        assertThat(length1).isEqualTo(length2);
        assertThat(length1).isNotEqualTo(different);

        // Test hashCode
        assertThat(length1.hashCode()).isEqualTo(length2.hashCode());
        assertThat(length1.hashCode()).isNotEqualTo(different.hashCode());

        // Test with null
        assertThat(length1).isNotEqualTo(null);

        // Test with different class
        assertThat(length1).isNotEqualTo("not a CryptographicLength");
    }

    @Test
    @DisplayName("should support deprecated of(AttributeValue) method")
    @SuppressWarnings("deprecation")
    void shouldSupportDeprecatedOfMethod() {
        AttributeValue attrValue = AttributeValue.of(384);
        AttributeName attributeName = AttributeName.of("Cryptographic Length");
        CryptographicLength length = CryptographicLength.of(attributeName, attrValue);
        assertThat(length.getValue()).isEqualTo(384);
    }
}
