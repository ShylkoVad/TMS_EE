package by.teachmeskills.hw_23062023.memento;

public class TextEditor {
    private String text;

    public Memento memento () {
        return new Memento(text);
    }
    public void load (Memento memento) {
        text = memento.getTEXT();
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextEditor{" +
                "text='" + text + '\'' +
                '}';
    }
}
