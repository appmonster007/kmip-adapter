package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.Comment;

/**
 * XML deserializer for {@link Comment}.
 */
public class CommentXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Comment, Comment.CommentBuilder> {

  /**
   * Constructs a new {@link CommentXmlDeserializer}.
   */
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