package tt.operations;

import java.util.Formatter;
import java.util.Objects;
import java.util.Optional;

public record Pair(Operation op1, Operation op2) {
    public Optional<Pair> getTransitionPair(Pair otherPair) {
        if (this.op2.equals(otherPair.op1)) {
            return Optional.of(new Pair(this.op1, otherPair.op2));
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return new Formatter().format("(%s, %s)", op1, op2).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(op1, pair.op1) && Objects.equals(op2, pair.op2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(op1, op2);
    }
}
