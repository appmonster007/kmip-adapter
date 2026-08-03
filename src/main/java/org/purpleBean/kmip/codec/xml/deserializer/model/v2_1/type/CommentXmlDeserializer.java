package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.Comment;

public class CommentXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Comment, Comment.CommentBuilder> {

  public CommentXmlDeserializer() {
    super(Comment.kmipTag, Comment.encodingType);
  }

  @Override
  protected Comment.CommentBuilder createBuilder() {
    return Comment.builder();
  }

  @Override
  protected void setValue(Comment.CommentBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Comment build(Comment.CommentBuilder builder) {
    return builder.build();
  }
}