package com.ssafy.ranktrack;

import lombok.Getter;

@Getter
public enum Tier {
    UNRATED(0, "언레이티드", 0),
    BRONZE_5(1, "브론즈 V", 30),
    BRONZE_4(2, "브론즈 IV", 60),
    BRONZE_3(3, "브론즈 III", 90),
    BRONZE_2(4, "브론즈 II", 120),
    BRONZE_1(5, "브론즈 I", 150),
    SILVER_5(6, "실버 V", 200),
    SILVER_4(7, "실버 IV", 300),
    SILVER_3(8, "실버 III", 400),
    SILVER_2(9, "실버 II", 500),
    SILVER_1(10, "실버 I", 650),
    GOLD_5(11, "골드 V", 800),
    GOLD_4(12, "골드 IV", 950),
    GOLD_3(13, "골드 III", 1100),
    GOLD_2(14, "골드 II", 1250),
    GOLD_1(15, "골드 I", 1400),
    PLATINUM_5(16, "플래티넘 V", 1600),
    PLATINUM_4(17, "플래티넘 IV", 1750),
    PLATINUM_3(18, "플래티넘 III", 1900),
    PLATINUM_2(19, "플래티넘 II", 2000),
    PLATINUM_1(20, "플래티넘 I", 2100),
    DIAMOND_5(21, "다이아몬드 V", 2200),
    DIAMOND_4(22, "다이아몬드 IV", 2300),
    DIAMOND_3(23, "다이아몬드 III", 2400),
    DIAMOND_2(24, "다이아몬드 II", 2500),
    DIAMOND_1(25, "다이아몬드 I", 2600),
    RUBY_5(26, "루비 V", 2700),
    RUBY_4(27, "루비 IV", 2800),
    RUBY_3(28, "루비 III", 2850),
    RUBY_2(29, "루비 II", 2900),
    RUBY_1(30, "루비 I", 2950),
    MASTER(31, "마스터", 3000);

    private final int value;
    private final String name;
    private final int score;

    Tier(int value, String name, int score) {
        this.value = value;
        this.name = name;
        this.score = score;
    }

    public static Tier fromValue(int value) {
        for (Tier tier : values()) {
            if (tier.value == value) {
                return tier;
            }
        }
        return UNRATED; // 기본 값 설정
    }

    public static Tier fromScore(long score) {
        Tier result = UNRATED;
        for (Tier tier : values()) {
            if (score >= tier.getScore()) {
                result = tier;
            } else {
                break;
            }
        }
        return result;
    }
}
