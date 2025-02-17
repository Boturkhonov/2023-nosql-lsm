package ru.vk.itmo.boturkhonovkamron;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.Comparator;

/**
 * Comparator realization to compare MemorySegment objects.
 *
 * @author Kamron Boturkhonov
 * @since 2023.09.27
 */
public enum MemorySegmentComparator implements Comparator<MemorySegment> {

    COMPARATOR {

        @Override
        public int compare(final MemorySegment left, final MemorySegment right) {
            if (left.equals(right)) {
                return 0;
            }
            final long leftSize = left.byteSize();
            final long rightSize = right.byteSize();
            final long mismatch = left.mismatch(right);
            if (mismatch >= 0 && leftSize != mismatch && rightSize != mismatch) {
                return Byte.compare(left.get(ValueLayout.JAVA_BYTE, mismatch),
                        right.get(ValueLayout.JAVA_BYTE, mismatch));
            }
            return Long.compare(leftSize, rightSize);
        }
    }
}
