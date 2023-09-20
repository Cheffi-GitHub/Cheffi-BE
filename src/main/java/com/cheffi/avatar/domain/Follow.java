package com.cheffi.avatar.domain;

import com.cheffi.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Follow extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Avatar subject;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private Avatar target;

    private Follow(Avatar subject, Avatar target) {
        this.subject = subject;
        this.target = target;
    }

    public static Follow createFollowRelationship(Avatar subject, Avatar target) {

        isSelfFollowAttempt(subject, target);
        return new Follow(subject, target);
    }

    private static void isSelfFollowAttempt(Avatar subject, Avatar target) {

        if (subject.getId() == target.getId()) {
            throw new IllegalArgumentException("자신을 팔로우하는 작업은 허용되지 않습니다.");
        }
    }

}
