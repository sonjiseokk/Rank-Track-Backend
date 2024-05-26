package com.ssafy.ranktrack;

import lombok.Getter;

@Getter
public enum Tier {
    UNRATED(0, "언레이티드"),
    BRONZE_5(1, "브론즈 V"),
    BRONZE_4(2, "브론즈 IV"),
    BRONZE_3(3, "브론즈 III"),
    BRONZE_2(4, "브론즈 II"),
    BRONZE_1(5, "브론즈 I"),
    SILVER_5(6, "실버 V"),
    SILVER_4(7, "실버 IV"),
    SILVER_3(8, "실버 III"),
    SILVER_2(9, "실버 II"),
    SILVER_1(10, "실버 I"),
    GOLD_5(11, "골드 V"),
    GOLD_4(12, "골드 IV"),
    GOLD_3(13, "골드 III"),
    GOLD_2(14, "골드 II"),
    GOLD_1(15, "골드 I"),
    PLATINUM_5(16, "플래티넘 V"),
    PLATINUM_4(17, "플래티넘 IV"),
    PLATINUM_3(18, "플래티넘 III"),
    PLATINUM_2(19, "플래티넘 II"),
    PLATINUM_1(20, "플래티넘 I"),
    DIAMOND_5(21, "다이아몬드 V"),
    DIAMOND_4(22, "다이아몬드 IV"),
    DIAMOND_3(23, "다이아몬드 III"),
    DIAMOND_2(24, "다이아몬드 II"),
    DIAMOND_1(25, "다이아몬드 I"),
    RUBY_5(26, "루비 V"),
    RUBY_4(27, "루비 IV"),
    RUBY_3(28, "루비 III"),
    RUBY_2(29, "루비 II"),
    RUBY_1(30, "루비 I"),
    MASTER(31, "마스터");

    private final int value;
    private final String name;

    Tier(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Tier fromValue(int value) {
        for (Tier tier : values()) {
            if (tier.value == value) {
                return tier;
            }
        }
        return UNRATED; // 기본 값 설정
    }

}
