import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class App {
    
    
    static String nomes[] = new String[5];
    static double notas[][] = new double[5][3];

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
                    //mostrarTabelaNotas();
                    break;
                case 4:
                    //mostrarAprovados();
                    break;
                case 5:
                    //mostrarMaiorMedia();
                    break;
                case 6:
                    //mostrarMediaGeral();
                    break;
                case 7:
                    //mostrarDisciplinaMaiorMedia();
                    break;
                case 8:
                    //mostrarAprovadosOrdemAlfabetica();
                    break;
                case 9:
                    //mostrarReprovadosOrdemAlfabetica();
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
            System.out.println("Digite o nome do aluno " + (i + 1) + ":");
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
            System.out.println("Digite as notas do aluno " + nomes[i] + ":");
            for (int j = 0; j < notas[i].length; j++) {
                System.out.println("Nota " + (j + 1) + ":");
                try {
                    notas[i][j] = sc.nextDouble();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                    sleep(3000);
                    sc.nextLine(); //buffer
                    j--; // volta pra mesma nota
                }
            }
        }
    }
    
    
    
    public static void main(String[] args) throws Exception {
        menu();
   
   
   
   
   
   
    }
}
