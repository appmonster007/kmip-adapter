package org.purpleBean.kmip.benchmark;

import org.openjdk.jmh.annotations.*;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.benchmark.util.BenchmarkSubjects;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * JMH benchmark harness delegating to pluggable KmipBenchmarkSubject implementations.
 * Add a new subject by implementing KmipBenchmarkSubject and registering it below.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
public class KmipSerializationBenchmark {

    // JSON serialize/deserialize
    @Benchmark
    public String jsonSerialize(BenchState s) throws Exception {
        return s.impl.jsonSerialize();
    }

    @Benchmark
    public Object jsonDeserialize(BenchState s) throws Exception {
        return s.impl.jsonDeserialize();
    }

    // XML serialize/deserialize
    @Benchmark
    public String xmlSerialize(BenchState s) throws Exception {
        return s.impl.xmlSerialize();
    }

    @Benchmark
    public Object xmlDeserialize(BenchState s) throws Exception {
        return s.impl.xmlDeserialize();
    }

    // TTLV serialize/deserialize
    @Benchmark
    public ByteBuffer ttlvSerialize(BenchState s) throws Exception {
        return s.impl.ttlvSerialize();
    }

    @Benchmark
    public Object ttlvDeserialize(BenchState s) throws Exception {
        return s.impl.ttlvDeserialize();
    }

    @org.openjdk.jmh.annotations.State(Scope.Benchmark)
    public static class BenchState {

        private static final Map<String, KmipBenchmarkSubject> REGISTRY = new HashMap<>();

        static {
            REGISTRY.putAll(BenchmarkSubjects.discoverMap());
            if (REGISTRY.isEmpty()) {
                throw new IllegalStateException(
                        "No KmipBenchmarkSubject implementations discovered. " +
                                "Ensure you have ServiceLoader registrations under src/test/resources/META-INF/services/" +
                                "org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject");
            }
            System.out.println("Discovered benchmark subjects: " + REGISTRY.keySet());
        }

        // Select which subject to benchmark. Populated by JmhBenchmarkRunner via OptionsBuilder.param("subject", ...)
        // Default placeholder is only a guard in case the runner does not set params.
        @Param({
                "__AUTO__"
        })
        public String subject;
        private KmipBenchmarkSubject impl;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            if ("__AUTO__".equals(subject)) {
                throw new IllegalStateException("Benchmark param 'subject' was not set by the runner. " +
                        "Ensure you are invoking JmhBenchmarkRunner which auto-discovers subjects and injects them via OptionsBuilder.param().");
            }
            impl = REGISTRY.get(subject);
            if (impl == null) {
                throw new IllegalArgumentException("Unknown subject: " + subject + ". Available: " + REGISTRY.keySet());
            }
            impl.setup();
        }

        @TearDown(Level.Trial)
        public void tearDown() {
            if (impl != null) impl.tearDown();
        }
    }
}
