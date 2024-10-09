package br.com.vitheka.doubt_test_restclient_with_two_beans.response;

public record BraApiResponse(
        String cep,
        String state,
        String city,
        String neighborhood,
        String street,
        String service,
        Location location
) {
    public static record Location(String type, Coordinates coordinates) {}

    public static record Coordinates(double longitude, double latitude) {}
}

