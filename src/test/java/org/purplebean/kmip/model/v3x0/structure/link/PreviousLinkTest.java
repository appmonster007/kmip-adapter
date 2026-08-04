package org.purplebean.kmip.model.v3x0.structure.link;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.function.BiFunction;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purplebean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("PreviousLink Domain Tests")
class PreviousLinkTest extends AbstractKmipDataTypeTestSuite<PreviousLink>
    implements KmipAttributeTestSuite<PreviousLink> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<PreviousLink> type() {
    return PreviousLink.class;
  }

  @Override
  public PreviousLink createDefault() {
    return PreviousLink.of("test-id");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.REFERENCE;
  }

  @Override
  public boolean expectAlwaysPresent() {
    return false;
  }

  @Override
  public boolean expectServerInitializable() {
    return true;
  }

  @Override
  public boolean expectClientInitializable() {
    return true;
  }

  @Override
  public boolean expectClientDeletable() {
    return true;
  }

  @Override
  public boolean expectMultiInstanceAllowed() {
    return false;
  }

  @Override
  public State stateForServerModifiableTrue() {
    return State.Standard.ACTIVE.inst(); // Always modifiable
  }

  @Override
  public State stateForServerModifiableFalse() {
    return null; // Always modifiable
  }

  @Override
  public State stateForClientModifiableTrue() {
    return State.Standard.ACTIVE.inst(); // Always modifiable
  }

  @Override
  public State stateForClientModifiableFalse() {
    return null; // Always modifiable
  }

  @Override
  public AttributeValue expectedAttributeValue() {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.REFERENCE)
        .value("test-id")
        .build();
  }

  @Override
  public void attribute_serverModifiable_respectsState() {
    // Always true
  }

  @Override
  public void attribute_clientModifiable_respectsState() {
    // Always true
  }

  // The legacy core.structure.Attribute wrapper used by the default attribute_roundTrip()
  // only supports KMIP V1.1/V1.2 (it was superseded by NewAttribute in V2.1+), so a V3.0-only
  // attribute like PreviousLink round-trips directly through the KmipAttribute registry instead.
  @Override
  public void attribute_roundTrip() {
    PreviousLink obj = createDefault();
    BiFunction<AttributeName, AttributeValue, ? extends KmipAttribute> builder =
        KmipAttribute.getAttributeBuilderFromRegistry(KmipTag.Standard.PREVIOUS_LINK,
            obj.getEncodingType());
    KmipAttribute reconstructed = builder.apply(obj.getAttributeName(), obj.getAttributeValue());
    assertThat(reconstructed.getAttributeValue())
        .as("AttributeValue equality")
        .isEqualTo(obj.getAttributeValue());
    assertThat(reconstructed.getAttributeName())
        .as("AttributeName equality")
        .isEqualTo(obj.getAttributeName());
  }
}
