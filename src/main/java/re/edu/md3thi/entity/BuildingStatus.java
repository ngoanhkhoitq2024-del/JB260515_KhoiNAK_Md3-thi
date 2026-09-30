package re.edu.md3thi.entity;

public enum BuildingStatus {
    ĐÃ_HỦY_BỎ(0), ĐANG_THỰC_HIỆN(1), ĐÃ_HOÀN_THÀNH(2);

    private final short value;

    BuildingStatus(int value) {
        this.value = (short) value;
    }

    public short getValue() {
        return value;
    }
}
