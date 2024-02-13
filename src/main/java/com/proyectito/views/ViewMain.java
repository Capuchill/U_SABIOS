package com.proyectito.views;

import java.util.Scanner;

import com.proyectito.repository.impl.implPeriodo.RepositoryPeriodoMysqlImpl;
import com.proyectito.repository.impl.implProfesor.RepositoryProfesorMysqlImpl;
import com.proyectito.repository.impl.implalumno.RepositoryAlumnoMysqlImpl;
import com.proyectito.repository.impl.implcursos.RepositoryCursoMysqlImpl;
import com.proyectito.repository.impl.impldepartamento.RepositoryDepartamentoMysqlImpl;
import com.proyectito.repository.impl.implsalon.RepositorySalonMysqlImpl;
import com.proyectito.services.ServiceAlumno;
import com.proyectito.services.ServiceCurso;
import com.proyectito.services.ServiceDepartamento;
import com.proyectito.services.ServicePeriodo;
import com.proyectito.services.ServiceProfesor;
import com.proyectito.services.ServiceSalon;
import com.proyectito.services.impl.ServiceAlumnoImpl;
import com.proyectito.services.impl.ServiceCursoImpl;
import com.proyectito.services.impl.ServiceDepartamentoImpl;
import com.proyectito.services.impl.ServicePeriodoImpl;
import com.proyectito.services.impl.ServiceProfesorImpl;
import com.proyectito.services.impl.ServiceSalonImpl;
import com.proyectito.utils.TextStyles;

public class ViewMain {
    public static final ServiceDepartamento serviceDepartamento = new ServiceDepartamentoImpl(new RepositoryDepartamentoMysqlImpl());
    public static final ServiceSalon serviceSalon = new ServiceSalonImpl(new RepositorySalonMysqlImpl());
    public static final ServiceCurso serviceCurso = new ServiceCursoImpl(new RepositoryCursoMysqlImpl());
    public static final ServicePeriodo servicePeriodo = new ServicePeriodoImpl(new RepositoryPeriodoMysqlImpl());
    public static final ServiceAlumno serviceAlumno = new ServiceAlumnoImpl(new RepositoryAlumnoMysqlImpl());
    public static final ServiceProfesor serviceProfesor = new ServiceProfesorImpl(new RepositoryProfesorMysqlImpl());
    public static final Scanner leer = new Scanner(System.in);
    
    public static void main(String[] args) {

        int op = 0;

        do {
            op = menuMain();
            switch (op) {
                case 1:
                    ViewDepartamento.startMenu();
                    break;
                case 2:
                    ViewSalon.startMenu();
                    break;
                case 3:
                    ViewCurso.startMenu();
                    break;
                case 4:
                    ViewPeriodo.startMenu();
                    break;

                case 5:
                    ViewAlumno.startMenu();
                    break;
                case 6:
                    ViewProfesor.startMenu();
                    break;
                default:
                    TextStyles.msgError("Opcion no valida");
                    break;
            }
        } while (op>=1 && op<11);


    }

    public static int menuMain(){
        
        System.out.println("\n");
        TextStyles.prettyTitle("GESTION DE MATRICULAS U SABIOS");
        System.out.println("\n");
        TextStyles.opMenu("Modulo de Departamento", 1);
        TextStyles.opMenu("Modulo de Salon", 2);
        TextStyles.opMenu("Modulo de Curso", 3);
        TextStyles.opMenu("Modulo de Periodo", 4);
        TextStyles.opMenu("Modulo Estudiante", 5);
        TextStyles.opMenu("Modulo Profesor", 6);

        
        System.out.print("\n-> Opcion: ");
        return leer.nextInt();
    }
}