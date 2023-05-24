package Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLabelListExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Label List Example");
        
        DefaultListModel<ImageLabelItem> listModel = new DefaultListModel<>();
        listModel.addElement(new ImageLabelItem(getImage("image/돋보기.jpeg"), "항목 1"));
        listModel.addElement(new ImageLabelItem(getImage("image/종.jpeg"), "항목 2"));
        listModel.addElement(new ImageLabelItem(getImage("image/목록.jpeg"), "항목 3"));
        listModel.addElement(new ImageLabelItem(getImage("image/damoa.jpeg"), "항목 4"));

        JList<ImageLabelItem> list = new JList<>(listModel);
        list.setCellRenderer(new ImageLabelListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 50, 400, 452);

        frame.getContentPane().add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    // 이미지를 로드하여 BufferedImage로 반환하는 메서드
    private static BufferedImage getImage(String fileName) {
    	try {
            // 이미지 파일을 로드하여 BufferedImage로 변환
            return ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // 이미지 로드에 실패하면 null 반환
    }
}

class ImageLabelItem {
    private BufferedImage image;
    private String label;

    public ImageLabelItem(BufferedImage image, String label) {
        this.image = image;
        this.label = label;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getLabel() {
        return label;
    }
}

class ImageLabelListCellRenderer extends JPanel implements ListCellRenderer<ImageLabelItem> {
    private JLabel imageLabel;
    private JLabel textLabel;

    public ImageLabelListCellRenderer() {
        setLayout(new BorderLayout());
        setOpaque(true);

        imageLabel = new JLabel();
        textLabel = new JLabel();

        add(imageLabel, BorderLayout.WEST);
        add(textLabel, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ImageLabelItem> list, ImageLabelItem value,
            int index, boolean isSelected, boolean cellHasFocus) {
        imageLabel.setIcon(new ImageIcon(value.getImage()));
        textLabel.setText(value.getLabel());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}