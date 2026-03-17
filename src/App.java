import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class App {
    
    
    static String nomes[] = new String[5];
    static double notas[][] = new double[5][3];
    static String[] disciplinas = {"Matemática", "Português", "Ciências"};

    static Scanner sc = new Scanner(System.in);
    
    static ArrayList<String> aprovados = new ArrayList<>();
    static ArrayList<String> reprovados = new ArrayList<>();

    public static void limpa() {  
        System.out.print("\033\143");    
        System.out.flush();
    }  

    public static void menu() throws InterruptedException {
        
        int opcao = -1;
        do {
            limpa();
            System.out.println("=== INÍCIO ===\n\n1-Cadastrar Aluno\n2-Cadastrar Nota\n3-Mostrar Tabela de Notas\n4-Mostrar Alunos Aprovados\n5-Mostrar alunos com maior média\n6-Mostrar média geral\n7-Mostrar disciplina com maior média\n8-Mostrar aprovados em ordem alfabética\n9-Mostrar alunos reprovados em ordem alfabética\n0-Sair");
            System.out.println("Digite a opção desejada:");
            
            

            try {
                opcao = sc.nextInt();
            } catch (Exception e) {
                limpa();
                System.out.println("Entrada inválida. Por favor, digite um número.");
                sleep(3000);
                sc.nextLine(); //limpa buffer
                continue;
            }
            

            switch (opcao) {
                case 1:
                    limpa();
                    cadastrarAluno();
                    break;
                case 2:
                    limpa();
                    cadastrarNota();
                    break;
                case 3:
                    limpa();
                    mostrarTabelaNotas();
                    break;
                case 4:
                    limpa();
                    mostrarAprovados();
                    break;
                case 5:
                    limpa();
                    mostrarMaiorMedia();
                    break;
                case 6:
                    limpa();
                    mostrarMediaGeral();
                    break;
                case 7:
                    limpa();
                    mostrarDisciplinaMaiorMedia();
                    break;
                case 8:
                    limpa();
                    mostrarAprovadosOrdemAlfabetica();
                    break;
                case 9:
                    limpa();
                    mostrarReprovadosOrdemAlfabetica();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        
            
    }
    
    public static void cadastrarAluno() {
        sc.nextLine(); //limpa buffer
        for (int i = 0; i < nomes.length; i++) {
            System.out.println("Digite o nome do(a) aluno(a) " + (i + 1) + ":");
            nomes[i] = sc.nextLine();
        }

    }
    
    public static void cadastrarNota() throws InterruptedException {
        if (nomes[0] == null) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre os alunos primeiro.");
            sleep(3000);
            return;
        }
        for (int i = 0; i < notas.length; i++) {
            limpa();
            System.out.println("Digite as notas do(a) aluno(a) " + nomes[i] + ":");
            for (int j = 0; j < notas[i].length; j++) {
                System.out.print(disciplinas[j] + " = ");
                try {
                    notas[i][j] = sc.nextDouble();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                    sleep(3000);
                    sc.nextLine(); //buffer, se tirar fica em loop infinito
                    j--; // volta pra mesma nota
                }  
            }
        }
    }

    public static double calcularMedia(int idx) {
        if (idx < 0 || idx >= notas.length) {
            return 0;
        }
        double soma = 0;
        for (int j = 0; j < notas[idx].length; j++) {
            soma += notas[idx][j];
        }
        return soma / 3;
    }

    public static void aprovarAluno(int idx){
        double media = calcularMedia(idx);
        if (media >= 7) {
            if (!aprovados.contains(nomes[idx])) {
                aprovados.add(nomes[idx]);
            }
        } else {
            if (!reprovados.contains(nomes[idx])) {
                reprovados.add(nomes[idx]);
            }
        }
    }

    public static void mostrarTabelaNotas() throws InterruptedException {
        if (nomes[0] == null) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre os alunos primeiro.");
            sleep(3000);
            return;
        }

        for (int i = 0; i < nomes.length; i++) {
            if (nomes[i] == null) continue; // pula se não cadastrado
            limpa();
            System.out.println("Tabela de Notas - Aluno: " + nomes[i]);
            System.out.println("==========================");

            // cabeçalho
            System.out.printf("%-15s %-10s\n", "Disciplina", "Nota");
            System.out.println("-------------------------");

            // dados
            for (int j = 0; j < notas[i].length; j++) {
                System.out.printf("%-15s %-10.2f\n", disciplinas[j], notas[i][j]);
            }

            double media = calcularMedia(i);
            System.out.println("-------------------------");
            System.out.printf("%-15s %-10.2f\n", "Média", media);

            System.out.println("\nPressione Enter para continuar...");
            sc.nextLine(); // limpa buffer
            sc.nextLine(); // pausa
        }
    }

    public static void mostrarAprovados() throws InterruptedException{
        if (nomes[0] == null) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre os alunos primeiro.");
            sleep(3000);
            return;
        }
        // garantir que nao repita
        aprovados.clear();
        reprovados.clear();
        for (int i = 0; i < nomes.length; i++) {
            aprovarAluno(i);
        }
        System.out.println("Alunos aprovados:");
        for (String nome : aprovados) {
            System.out.println("- " + nome);
        }
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine(); // limpa buffer
        sc.nextLine(); // pausa

    }

    public static void mostrarMaiorMedia() throws InterruptedException {
        if (nomes[0] == null) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre os alunos primeiro.");
            sleep(3000);
            return;
        }
        double maiorMedia = 0;
        String alunoMaiorMedia = "";
        for (int i = 0; i < nomes.length; i++) {
            double media = calcularMedia(i);
            if (media > maiorMedia) {
                maiorMedia = media;
                alunoMaiorMedia = nomes[i];
            }
        }
        System.out.println("Aluno com maior média: " + alunoMaiorMedia + " (" + String.format("%.2f", maiorMedia) + ")");
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine(); // limpa buffer
        sc.nextLine(); // pausa
        

    }

    public static void mostrarMediaGeral() throws InterruptedException {
        if (nomes[0] == null) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre os alunos primeiro.");
            sleep(3000);
            return;
        }
        double somaMedias = 0;
        for (int i = 0; i < nomes.length; i++) {
            somaMedias += calcularMedia(i);
        }
        double mediaGeral = somaMedias / nomes.length;
        System.out.println("Média geral da turma: " + String.format("%.2f", mediaGeral));
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine(); // limpa buffer
        sc.nextLine(); // pausa
    }

    public static void mostrarDisciplinaMaiorMedia() throws InterruptedException {
        if (nomes[0] == null) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre os alunos primeiro.");
            sleep(3000);
            return;
        }
        double maiorMediaDisciplina = 0;
        String disciplinaMaiorMedia = "";
        for (int j = 0; j < disciplinas.length; j++) {
            double somaNotas = 0;
            for (int i = 0; i < nomes.length; i++) {
                somaNotas += notas[i][j];
            }
            double mediaDisciplina = somaNotas / nomes.length;
            if (mediaDisciplina > maiorMediaDisciplina) {
                maiorMediaDisciplina = mediaDisciplina;
                disciplinaMaiorMedia = disciplinas[j];
            }
        }
        System.out.println("Disciplina com maior média: " + disciplinaMaiorMedia + " (" + String.format("%.2f", maiorMediaDisciplina) + ")");
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine(); // limpa buffer
        sc.nextLine(); // pausa
    }

    public static void mostrarAprovadosOrdemAlfabetica() throws InterruptedException {
        if (nomes[0] == null) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre os alunos primeiro.");
            sleep(3000);
            return;
        }

        Collections.sort(aprovados);
        System.out.println("Alunos aprovados em ordem alfabética:");
        for (String nome : aprovados) {
            System.out.println("- " + nome);
        }
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine(); // limpa buffer
        sc.nextLine(); // pausa

    }

    public static void mostrarReprovadosOrdemAlfabetica() throws InterruptedException {
        if (nomes[0] == null) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre os alunos primeiro.");
            sleep(3000);
            return;
        }

        Collections.sort(reprovados);
        System.out.println("Alunos reprovados em ordem alfabética:");
        for (String nome : reprovados) {
            System.out.println("- " + nome);
        }
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine(); // limpa buffer
        sc.nextLine(); // pausa

    }


    public static void main(String[] args) throws Exception {
        menu();
   
   
   
   
   
   
    }
}
