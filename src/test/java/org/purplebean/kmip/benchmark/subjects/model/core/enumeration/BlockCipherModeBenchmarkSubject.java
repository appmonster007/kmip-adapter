package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.BlockCipherMode;

/**
 * Benchmark subject for {@link BlockCipherMode}.
 */
public class BlockCipherModeBenchmarkSubject extends KmipBenchmarkSubject<BlockCipherMode> {

  /**
   * Constructs a new {@link BlockCipherModeBenchmarkSubject}.
   */
  public BlockCipherModeBenchmarkSubject() throws Exception {
    BlockCipherMode blockCipherMode = BlockCipherMode.Standard.CBC.inst();
    initialize(blockCipherMode, BlockCipherMode.class);
  }

  @Override
  public String name() {
    return "BlockCipherMode";
  }

}
