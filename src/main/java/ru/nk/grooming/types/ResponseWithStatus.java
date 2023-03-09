package ru.nk.grooming.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWithStatus <T> {
    private int statusCode;
    private T data;

    public static <DataType> Builder<DataType> builder() {
        return new Builder<>();
    }

    public static class Builder <T> {
        private int statusCode;
        private T data;
        public Builder<T> statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResponseWithStatus<T> build() {
            return new ResponseWithStatus<>(statusCode, data);
        }
    }
}
