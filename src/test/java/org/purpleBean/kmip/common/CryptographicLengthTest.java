package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.AttributeValue;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeAttributeTestSuite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("CryptographicLength Domain Tests")
class CryptographicLengthTest extends AbstractKmipDataTypeAttributeTestSuite<CryptographicLength> {

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
        return State.Standard.PRE_ACTIVE.inst();
    }

    @Override
    protected State stateForServerModifiableFalse() {
        // Return a state where server modification is not allowed
        return State.Standard.ACTIVE.inst();
    }

    @Override
    protected State stateForClientModifiableTrue() {
        // Return a state where client modification is allowed
        return State.Standard.PRE_ACTIVE.inst();
    }

    @Override
    protected State stateForClientModifiableFalse() {
        // Return a state where client modification is not allowed
        return State.Standard.ACTIVE.inst();
    }

    @Test
    @DisplayName("should create from attribute value")
    void shouldCreateFromAttributeValue() {
        AttributeValueInteger attrValue = AttributeValueInteger.of(192);
        CryptographicLength length = CryptographicLength.of(AttributeName.of("Cryptographic Length"), attrValue);
        assertThat(length.getValue()).isEqualTo(192);
    }

    @Test
    @DisplayName("should throw for invalid attribute value type")
    void shouldThrowForInvalidAttributeValueType() {
        AttributeValueTextString invalidAttrValue = AttributeValueTextString.of("invalid");
        assertThatThrownBy(() -> CryptographicLength.of(AttributeName.of("Cryptographic Length"), invalidAttrValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid attribute value");
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

        // Test with a sample state
        State preActiveState = State.Standard.PRE_ACTIVE.inst();
        assertThat(length.isServerModifiable(preActiveState)).isTrue();
        assertThat(length.isClientModifiable(preActiveState)).isTrue();

        State activeState = State.Standard.ACTIVE.inst();
        assertThat(length.isServerModifiable(activeState)).isFalse();
        assertThat(length.isClientModifiable(activeState)).isFalse();
    }

    @Test
    @DisplayName("should support KMIP specs")
    void shouldSupportKmipSpecs() {
        CryptographicLength length = createDefault();
        withKmipSpec(
                KmipSpec.V1_2,
                () -> assertThat(length.isSupported()).isTrue()
        );
        withKmipSpec(
                KmipSpec.UnknownVersion,
                () -> assertThat(length.isSupported()).isTrue()
        );
        // Should not support unsupported versions
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThat(length.isSupported()).isFalse()
        );
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
        assertThat(((AttributeValueInteger) attrValue).getValue()).isEqualTo(512);
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
}
