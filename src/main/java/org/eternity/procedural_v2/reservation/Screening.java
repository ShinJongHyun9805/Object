package org.eternity.procedural_v2.reservation;

import org.eternity.procedural_v2.generic.Money;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Screening {

    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Reservation reserve(Customer customer, int audienceCount) {
        Money fee = movie.calculateFee(this).times(audienceCount);

        return new Reservation(customer, this, audienceCount, fee);
    }

    // 영화 정가 반환
    public Money getFixedFee() {
        return movie.getFee();
    }

    // 영화 순서.
    public boolean isSequence(int sequence) {
        return this.sequence == sequence;

    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }
}
