import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Projeto {

    // Classe Pessoa
    static class Pessoa {
        String nome;
        LocalDate data;

        Pessoa(String nome, LocalDate data) {
            this.nome = nome;
            this.data = data;
        }

    }
    // Classe Funcionario 
    static class Funcionario extends Pessoa {
        BigDecimal salario;
        String funcao;

        Funcionario(String nome, LocalDate data, BigDecimal salario, String funcao) {
            super(nome, data);
            this.salario = salario;
            this.funcao = funcao;
        }


    }
    
    public static void main(String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 – Inserir funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 – Remover João da lista
        funcionarios.removeIf(f -> f.nome.equals("João"));


        // 3.3 – Imprimir informações de todos funcionários
        System.out.println("\n  Lista de Funcionários ");
        for (Funcionario f : funcionarios) {
            System.out.printf("Nome: %s  Nascimento: %s  Função: %s  Salário: %s\n",
                    f.nome, f.data.format(dtf), f.funcao, nf.format(f.salario));
        }

        // 3.4 – Aumento de 10% de salário para todos funcionários
        for (Funcionario f : funcionarios) {
            f.salario = f.salario.multiply(new BigDecimal("1.10"));
        }


        
        // 3.5 e 3.6 não consegui resolver, pois não aprendi ainda sobre MAP


        
        // 3.8 – Imprimir funcionários que fazem aniversário em outubro ou dezembro
        System.out.println("\n Aniversariantes em Outubro ");
        for (Funcionario f : funcionarios) {
            if (f.data.getMonthValue() == 10 ) {
                System.out.printf("%s  Nascimento: %s\n", f.nome, f.data.format(dtf));
            }
        }

        System.out.println("\n Aniversariantes em Dezembro ");
        for (Funcionario f : funcionarios) {
            if (f.data.getMonthValue() == 12) {
                System.out.printf("%s  Nascimento: %s\n", f.nome, f.data.format(dtf));
            }
        }

        // 3.9 – Funcionário mais velho
        Funcionario velho = funcionarios.get(0); // Assume que a lista não está vazia
        for (int i = 1; i < funcionarios.size(); i++) {
            Funcionario atual = funcionarios.get(i);
            if (atual.data.isBefore(velho.data)) {
                velho = atual;
            }
        }
        int idade = LocalDate.now().getYear() - velho.data.getYear();
        System.out.printf("\n Funcionário mais velho  \n%s  Idade: %d anos\n", velho.nome, idade);
        

        // 3.10 – Ordenar lista por nome

        List<Funcionario> alfabetica = new ArrayList<>(funcionarios);
        alfabetica.sort(new Comparator<Funcionario>() {
            public int compare(Funcionario a1, Funcionario a2) {
                return a1.nome.compareTo(a2.nome);
            }
        });

        System.out.println("\n Funcionários em ordem alfabética ");
        for (Funcionario a : alfabetica) {
            System.out.printf(a.nome + "\n");
        }

        // 3.11 – Salário total dos funcionários.
        
        BigDecimal total = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            total = total.add(f.salario);
        }
        System.out.printf("\n  Salário total:  %s \n", nf.format(total));

        // 3.12 – Salários mínimos de cada funcionário (R$1212.00)
        
        System.out.println("\n Quantidade de salários mínimos por funcionário ");
        for (Funcionario f : funcionarios) {
            BigDecimal quantidade = f.salario.divide(new BigDecimal("1212.00"), new MathContext(2));
            System.out.printf("%s ganha %.2f salários mínimos\n", f.nome, quantidade);
        }
    }
}
