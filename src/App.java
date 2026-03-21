import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class App {
    
    
    // Armazena os nomes dos alunos
    static String nomes[] = new String[5];
    // Armazena as notas de cada aluno em cada disciplina
    static double notas[][] = new double[5][3];
    // Disciplinas usadas no sistema
    static String[] disciplinas = {"Matemática", "Português", "Ciências"};

    // Scanner para entrada do usuário
    static Scanner sc = new Scanner(System.in);
    
    // Listas de alunos aprovados e reprovados, atualizadas conforme as notas
    static ArrayList<String> aprovados = new ArrayList<>();
    static ArrayList<String> reprovados = new ArrayList<>();

    // Limpa tela
    public static void limpa() {  
        System.out.print("\033\143");    
        System.out.flush();
    }  
    // Exibe o menu principal e executa ações conforme a opção do usuário
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
            String nome;
            do {
                System.out.println("Digite o nome do(a) aluno(a) " + (i + 1) + ":");
                nome = sc.nextLine().trim();
                if (nome.isEmpty()) {
                    System.out.println("Nome inválido. O nome não pode ser vazio. Tente novamente.");
                }
            } while (nome.isEmpty());
            nomes[i] = nome;
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
                double nota;
                while (true) {
                    System.out.print(disciplinas[j] + " = ");
                    try {
                        nota = sc.nextDouble();
                        if (nota < 0 || nota > 10) {
                            System.out.println("Nota inválida. Digite um valor entre 0 e 10.");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Entrada inválida. Por favor, digite um número.");
                        sleep(3000);
                        sc.nextLine(); // limpa buffer
                    }
                }
                notas[i][j] = nota;
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

    // Recalcula as listas de aprovados/reprovados a cada vez que mostra
    public static void atualizarListasAprovacao() {
        aprovados.clear();
        reprovados.clear();
        for (int i = 0; i < nomes.length; i++) {
            aprovarAluno(i);
        }
    }

    // Exibe a tabela completa de notas e médias
    public static void mostrarTabelaNotas() throws InterruptedException {
        if (nomes[0] == null) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre os alunos primeiro.");
            sleep(3000);
            return;
        }

        // cabeçalho 
        System.out.printf("%-20s", "Aluno");
        for (String d : disciplinas) {
            System.out.printf("%-12s", d);
        }
        System.out.printf("%-12s", "Média");
        System.out.println();

        // linha separadora
        for (int k = 0; k < 100; k++){ 
            System.out.print("-");
        }
        System.out.println();

        // dados
        for (int i = 0; i < nomes.length; i++) {
            System.out.printf("%-20s", nomes[i]);
            for (int j = 0; j < notas[i].length; j++) {
                System.out.printf("%-12.2f", notas[i][j]);
            }
            double media = calcularMedia(i);
            System.out.printf("%-12.2f", media);
            System.out.println();
        }

        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine(); // limpa buffer
        sc.nextLine(); // pausa
    }

    public static void mostrarAprovados() throws InterruptedException{
        if (nomes[0] == null) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre os alunos primeiro.");
            sleep(3000);
            return;
        }
        atualizarListasAprovacao();
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

        atualizarListasAprovacao();
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

        atualizarListasAprovacao();
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
