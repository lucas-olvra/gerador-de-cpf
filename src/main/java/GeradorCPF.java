import java.util.Random;

public class GeradorCPF {

    public static String gerarCPF() {
        Random random = new Random();
        int[] cpf = new int[9];

        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        int primeiroDigito = calcularDigitoVerificador(cpf, 10);
        cpf = adicionarDigito(cpf, primeiroDigito);

        int segundoDigito = calcularDigitoVerificador(cpf, 11);
        cpf = adicionarDigito(cpf, segundoDigito);

        return formatarCPF(cpf);
    }

    private static int calcularDigitoVerificador(int[] cpf, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < cpf.length; i++) {
            soma += cpf[i] * (pesoInicial - i);
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    private static int[] adicionarDigito(int[] cpf, int digito) {
        int[] novoCpf = new int[cpf.length + 1];
        System.arraycopy(cpf, 0, novoCpf, 0, cpf.length);
        novoCpf[novoCpf.length - 1] = digito;
        return novoCpf;
    }

    private static String formatarCPF(int[] cpf) {
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d",
                cpf[0], cpf[1], cpf[2],
                cpf[3], cpf[4], cpf[5],
                cpf[6], cpf[7], cpf[8],
                cpf[9], cpf[10]);
    }

    public static void main(String[] args) {
        String cpfValido = gerarCPF();
        System.out.println(cpfValido);
    }
}
