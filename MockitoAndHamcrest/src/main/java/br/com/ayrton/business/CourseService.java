package br.com.ayrton.business;

import java.util.List;

public interface CourseService {
    public List<String> retrieveCourses(String student);

    // public List<String> randomFunction(String student);
    // adicionando um novo metodo aqui, iria "quebrar" a compatibilidade com o stub
    // este é o maior problema do stub, não é nada prático

    public void deleteCourse(String course);

}
