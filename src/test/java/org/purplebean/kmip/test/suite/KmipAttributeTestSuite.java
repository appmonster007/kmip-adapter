package org.purplebean.kmip.test.suite;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.util.StringUtils;

/**
 * Interface defining the test contract for KMIP attributes.
 *
 * <p>This interface provides default test methods to validate the behavior of any class
 * implementing {@link KmipAttribute}. It covers capability flags, state-dependent
 * modifiability, attribute round-trip reconstruction, and validation of attribute
 * name, value, and canonical name.
 *
 * @param <T> The specific KMIP attribute type being tested.
 */
public interface KmipAttributeTestSuite<T extends KmipAttribute> {

  /**
   * Returns a default instance of the attribute for testing.
   *
   * @return A default instance of the attribute for testing.
   */
  T createDefault();

  /**
   * Returns the expected value for {@link KmipAttribute#isAlwaysPresent()}.
   *
   * @return Expected value for {@link KmipAttribute#isAlwaysPresent()}.
   */
  boolean expectAlwaysPresent();

  /**
   * Returns the expected value for {@link KmipAttribute#isServerInitializable()}.
   *
   * @return Expected value for {@link KmipAttribute#isServerInitializable()}.
   */
  boolean expectServerInitializable();

  /**
   * Returns the expected value for {@link KmipAttribute#isClientInitializable()}.
   *
   * @return Expected value for {@link KmipAttribute#isClientInitializable()}.
   */
  boolean expectClientInitializable();

  /**
   * Returns the expected value for {@link KmipAttribute#isClientDeletable()}.
   *
   * @return Expected value for {@link KmipAttribute#isClientDeletable()}.
   */
  boolean expectClientDeletable();

  /**
   * Returns the expected value for {@link KmipAttribute#isMultiInstanceAllowed()}.
   *
   * @return Expected value for {@link KmipAttribute#isMultiInstanceAllowed()}.
   */
  boolean expectMultiInstanceAllowed();

  /**
   * Returns a state for which {@link KmipAttribute#isServerModifiable(State)} should return
   * {@code true}.
   *
   * @return A {@link State} for which {@link KmipAttribute#isServerModifiable(State)} should
   *     return {@code true}.
   */
  State stateForServerModifiableTrue();

  /**
   * Returns a state for which {@link KmipAttribute#isServerModifiable(State)} should return
   * {@code false}.
   *
   * @return A {@link State} for which {@link KmipAttribute#isServerModifiable(State)} should
   *     return {@code false}.
   */
  State stateForServerModifiableFalse();

  /**
   * Returns a state for which {@link KmipAttribute#isClientModifiable(State)} should return
   * {@code true}.
   *
   * @return A {@link State} for which {@link KmipAttribute#isClientModifiable(State)} should
   *     return {@code true}.
   */
  State stateForClientModifiableTrue();

  /**
   * Returns a state for which {@link KmipAttribute#isClientModifiable(State)} should return
   * {@code false}.
   *
   * @return A {@link State} for which {@link KmipAttribute#isClientModifiable(State)} should
   *     return {@code false}.
   */
  State stateForClientModifiableFalse();

  /**
   * Returns the expected AttributeValue for the instance returned by createDefault().
   *
   * @return The expected AttributeValue for the instance returned by createDefault().
   */
  AttributeValue expectedAttributeValue();

  /**
   * Returns the expected name of the attribute. Defaults to Title Case of the KMIP Tag
   * description.
   *
   * @return The expected name of the attribute. Defaults to Title Case of the KMIP Tag
   *     description.
   */
  default String expectedAttributeName() {
    return StringUtils.convertPascalToTitleCase(createDefault()
        .getKmipTag()
        .getDescription());
  }

  @Test
  @DisplayName("Attribute: capability flags match expectations")
  default void attribute_capabilityFlags_matchExpectations() {
    T attr = createDefault();
    assertThat(attr.isAlwaysPresent())
        .as("isAlwaysPresent")
        .isEqualTo(expectAlwaysPresent());
    assertThat(attr.isServerInitializable())
        .as("isServerInitializable")
        .isEqualTo(expectServerInitializable());
    assertThat(attr.isClientInitializable())
        .as("isClientInitializable")
        .isEqualTo(expectClientInitializable());
    assertThat(attr.isClientDeletable())
        .as("isClientDeletable")
        .isEqualTo(expectClientDeletable());
    assertThat(attr.isMultiInstanceAllowed())
        .as("isMultiInstanceAllowed")
        .isEqualTo(expectMultiInstanceAllowed());
  }

  @Test
  @DisplayName("Attribute: server modifiable respects state")
  default void attribute_serverModifiable_respectsState() {
    T attr = createDefault();
    assertThat(attr.isServerModifiable(stateForServerModifiableTrue()))
        .as("isServerModifiable(true state)")
        .isTrue();
    assertThat(attr.isServerModifiable(stateForServerModifiableFalse()))
        .as("isServerModifiable(false state)")
        .isFalse();
  }

  @Test
  @DisplayName("Attribute: client modifiable respects state")
  default void attribute_clientModifiable_respectsState() {
    T attr = createDefault();
    assertThat(attr.isClientModifiable(stateForClientModifiableTrue()))
        .as("isClientModifiable(true state)")
        .isTrue();
    assertThat(attr.isClientModifiable(stateForClientModifiableFalse()))
        .as("isClientModifiable(false state)")
        .isFalse();
  }

  @Test
  @DisplayName("Attribute: get AttributeValue and rebuild the object")
  @SuppressWarnings("unchecked")
  default void attribute_roundTrip() {
    T obj = createDefault();
    Attribute attr = Attribute.of(obj);
    T reconstructed = (T) Attribute.toKmipAttribute(attr);
    assertThat(reconstructed)
        .as("Reconstructed attribute")
        .isNotNull();
    assertThat(reconstructed.getAttributeValue())
        .as("AttributeValue equality")
        .isEqualTo(obj.getAttributeValue());
    assertThat(reconstructed.getAttributeName())
        .as("AttributeName equality")
        .isEqualTo(obj.getAttributeName());
  }

  @Test
  @DisplayName("Attribute: getAttributeValue returns expected value")
  default void attribute_getAttributeValue_returnsExpectedValue() {
    T attr = createDefault();
    assertThat(attr.getAttributeValue())
        .as("AttributeValue")
        .isEqualTo(expectedAttributeValue());
  }

  @Test
  @DisplayName("Attribute: getAttributeName returns expected value")
  default void attribute_getAttributeName_returnsExpectedValue() {
    T attr = createDefault();
    assertThat(attr.getAttributeName())
        .as("AttributeName")
        .isNotNull();
    assertThat(attr
        .getAttributeName()
        .getValue())
        .as("AttributeName value")
        .isEqualTo(expectedAttributeName());
  }

  @Test
  @DisplayName("Attribute: getCanonicalName returns expected value")
  default void attribute_getCanonicalName_returnsExpectedValue() {
    T attr = createDefault();
    assertThat(attr.getCanonicalName())
        .as("CanonicalName")
        .isEqualTo(attr
            .getKmipTag()
            .getDescription());
  }
}
