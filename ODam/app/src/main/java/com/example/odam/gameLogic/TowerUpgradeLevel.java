package com.example.odam.gameLogic;

import com.example.odam.tower.Tower;

public enum TowerUpgradeLevel {
    BASE,
    UPGRADE_ONE,
    UPGRADE_TWO,
    UPGRADE_THREE {
        @Override
        public TowerUpgradeLevel next() {
            return null; // see below for options for this line
        };
    };

    public TowerUpgradeLevel next() {
        // No bounds checking required here, because the last instance overrides
        return values()[ordinal() + 1];
    }
}
