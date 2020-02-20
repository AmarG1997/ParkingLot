package com.bridgelabz.enumeration;

public enum Lot {

    LOT{
        @Override
        public String assignLOT(int slotNo) {
            if (slotNo >= 0 && slotNo <= 25) {
                return "A";
            }
            if (slotNo >= 26 && slotNo <= 50) {
                return "B";
            }
            if (slotNo >= 51 && slotNo <= 75) {
                return "B";
            }
            if (slotNo > 76 && slotNo <= 100){
                return "D";
            }
            return null;
        }
    };

    public abstract String assignLOT(int slot);
}
