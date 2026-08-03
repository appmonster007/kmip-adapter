package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.CryptographicDomainParameters;
import org.purplebean.kmip.model.core.type.Qlength;

public class CryptographicDomainParametersXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CryptographicDomainParameters,
        CryptographicDomainParameters.CryptographicDomainParametersBuilder> {

  public CryptographicDomainParametersXmlDeserializer() {
    super(CryptographicDomainParameters.kmipTag, CryptographicDomainParameters.encodingType);
  }

  @Override
  protected CryptographicDomainParameters.CryptographicDomainParametersBuilder createBuilder() {
    return CryptographicDomainParameters.builder();
  }

  @Override
  protected void setValue(
      CryptographicDomainParameters.CryptographicDomainParametersBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.QLENGTH -> builder.qlength(ctxt.readValue(p, Qlength.class));
      case KmipTag.Standard.RECOMMENDED_CURVE ->
          builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CryptographicDomainParameters build(
      CryptographicDomainParameters.CryptographicDomainParametersBuilder builder) {
    return builder.build();
  }
}