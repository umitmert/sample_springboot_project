package com.ibss.domain;

public enum TodoState {
	WAITING("Waiting"), COMPLETED("Completed"), POSTPONED("Postponed");
	
	private final String displayName;
	
	TodoState(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
