package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.Comment;

/**
 * Benchmark subject for {@link Comment}.
 */
public class CommentBenchmarkSubject extends KmipBenchmarkSubject<Comment> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link CommentBenchmarkSubject}.
   */
  public CommentBenchmarkSubject() throws Exception {
    Comment subject = Comment.of("default-string");
    initialize(subject, Comment.class);
  }

  @Override
  public String name() {
    return "Comment";
  }
}