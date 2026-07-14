import aed.core.controller.MainController;
import aed.core.view.MainWindow;
import aed.modules.arrays_1d.Array1DModule;
import aed.modules.arrays_2d.Array2DModule;
import aed.modules.arrays_3d.Array3DModule;
import aed.modules.singly_list.SinglyListModule;
import aed.modules.doubly_list.DoublyListModule;
import aed.modules.circular_list.CircularListModule;
import aed.modules.stack.StackModule;
import aed.modules.queue.QueueModule;
import aed.modules.binary_tree.BinaryTreeModule;
import aed.modules.bst.BSTModule;

public class main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            MainWindow window = new MainWindow();
            MainController controller = new MainController(window);
            controller.registerModule("arrays_1d", new Array1DModule());
            controller.registerModule("arrays_2d", new Array2DModule());
            controller.registerModule("arrays_3d", new Array3DModule());
            controller.registerModule("singly_list", new SinglyListModule());
            controller.registerModule("doubly_list", new DoublyListModule());
            controller.registerModule("circular_list", new CircularListModule());
            controller.registerModule("stack", new StackModule());
            controller.registerModule("queue", new QueueModule());
            controller.registerModule("binary_tree", new BinaryTreeModule());
            controller.registerModule("bst", new BSTModule());
        });
    }
}
