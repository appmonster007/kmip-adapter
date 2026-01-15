package org.purpleBean.kmip.api;

import java.util.function.Supplier;

/**
 * Manages the thread-local context for KMIP (Key Management Interoperability Protocol) operations.
 * <p>
 * This class provides a mechanism to set, get, and clear the current KMIP specification version
 * on a per-thread basis. This is crucial for the codec to correctly serialize and deserialize
 * data according to the rules of a specific KMIP version, as the representation of objects
 * and attributes can vary between specifications.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Thread-Local Storage:</b> Uses a {@link ThreadLocal} to ensure that the KMIP
 *       specification context is isolated to the current thread, preventing conflicts in
 *       multi-threaded environments.</li>
 *   <li><b>Context Management:</b> Provides static methods to {@link #setSpec(KmipSpec)},
 *       {@link #getSpec()}, and {@link #clear()} the current context.</li>
 *   <li><b>Scoped Operations:</b> The {@link #withSpec(KmipSpec, Supplier)} method offers a
 *       safe and convenient way to execute a block of code within a specific KMIP context,
 *       automatically restoring the previous context upon completion.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * Before performing any KMIP operations that involve serialization or deserialization, the
 * appropriate {@link KmipSpec} should be set in the context. The {@code withSpec} method is
 * the recommended way to manage this.
 *
 * <pre>
 * {@code
 * KmipContext.withSpec(KmipSpec.V1_2, () -> {
 *     // All KMIP operations within this lambda will use KMIP 1.2 rules.
 *     byte[] request = ttlvMapper.writeValueAsBytes(myRequest);
 *     // ...
 * });
 * }
 * </pre>
 *
 * @see KmipSpec
 * @see ThreadLocal
 */
public final class KmipContext {
    private static final ThreadLocal<KmipSpec> currentSpec = ThreadLocal.withInitial(() -> KmipSpec.UnknownVersion);

    // Prevent instantiation
    private KmipContext() {
    }

    /**
     * Gets the current KMIP specification for the current thread.
     *
     * @return the current {@link KmipSpec}, or {@link KmipSpec#UnknownVersion} if not set.
     */
    public static KmipSpec getSpec() {
        KmipSpec spec = currentSpec.get();
        return spec != null ? spec : KmipSpec.UnknownVersion;
    }

    /**
     * Sets the KMIP specification for the current thread.
     *
     * @param spec the {@link KmipSpec} to set. If {@code null}, the context is cleared.
     */
    public static void setSpec(KmipSpec spec) {
        if (spec == null) {
            clear();
        } else {
            currentSpec.set(spec);
        }
    }

    /**
     * Clears the current thread's KMIP specification and resets it to the default
     * ({@link KmipSpec#UnknownVersion}).
     */
    public static void clear() {
        currentSpec.remove();
        currentSpec.set(KmipSpec.UnknownVersion);
    }

    /**
     * Executes a block of code within a specific KMIP specification context.
     * <p>
     * This method sets the context to the provided {@link KmipSpec}, executes the given
     * {@link Supplier}, and ensures that the original context is restored afterward,
     * even if an exception occurs.
     *
     * @param spec     the {@link KmipSpec} to use for the duration of the operation.
     * @param supplier the code to execute, provided as a {@link Supplier}.
     * @param <T>      the result type of the supplier.
     * @return the value returned by the supplier.
     */
    public static <T> T withSpec(KmipSpec spec, Supplier<T> supplier) {
        KmipSpec previous = getSpec();
        try {
            setSpec(spec);
            return supplier.get();
        } finally {
            if (previous != null) {
                setSpec(previous);
            } else {
                clear();
            }
        }
    }
}
