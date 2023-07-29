package by.teachmeskills.hw_23062023.memento;

public class Project {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        Repository repository = new Repository();
        textEditor.setText("Первоначальный текст");
        System.out.println(textEditor);
        repository.setMemento(textEditor.memento());

        textEditor.setText("Измененный текст");
        System.out.println(textEditor);
        textEditor.load(repository.getMemento());
        System.out.println(textEditor);
    }
}
