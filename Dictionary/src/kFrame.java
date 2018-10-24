import TextToSpeech.TextToSpeech;
import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;
import javafx.scene.layout.BorderRepeat;
import jdk.jshell.execution.JdiDefaultExecutionControl;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.DesktopIconUI;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.ArrayList;

import static java.lang.System.out;

public class kFrame extends JFrame implements ActionListener, ListSelectionListener, DocumentListener{
    //TODO: khai bao cac bien giao dien
    JTextField txt_serch;
    JButton btn_removeWord;
    JButton btn_addWord;
    JList txt_list;
    JLabel txt_pane;
    JScrollPane scroll;
    JScrollPane scrollLabel;
    JButton btn_modifi;
    JButton VietAnh;
    JButton AnhViet;
    DefaultListModel<String> model;
    kWord Dic, dicva, dicav;
    ArrayList<String> listKey;

    JPanel addpanel;
    JComboBox<String> combo;
    JTextField addTextfile;
    JTextArea addTextArea;
    JButton btnadd;
    JButton btnCancel;

    JButton onlineS;

    JPanel modifiPanel;
    JTextArea modifiArea;
    JButton modifibtn;
    JButton modifiCancel;

    JButton btn_speech;

    //TODO: ICON
    Image iconAdd = new ImageIcon("src/add.png").getImage();

    Image iconSpeaker = new ImageIcon("src/speaker.png").getImage();
    Image iconRemove = new ImageIcon("src/remove.png").getImage();
    Image iconModify = new ImageIcon("src/modify.png").getImage();

    Image iconVA = new ImageIcon("src/vietanh.png").getImage();
    Image iconAV = new ImageIcon("src/anhviet.png").getImage();

    public void addList(String name){

        if(name.equals("E_V.txt")){
            listKey = dicav.getKeys();
            DefaultListModel<String> mo = new DefaultListModel<>();
            for (int i=0; i<dicav.getWordsNum(); i++){
                mo.addElement(listKey.get(i));
            }
            txt_list.setModel(mo);
            model = mo;
            Dic = dicav;
        }
        else if(name.equals("V_E.txt")) {
            listKey = dicva.getKeys();
            DefaultListModel<String> mo = new DefaultListModel<>();
            for (int i = 0; i < dicva.getWordsNum(); i++) {
                mo.addElement(listKey.get(i));
            }
            txt_list.setModel(mo);
            model = mo;
            Dic = dicva;
        }
    }
    public kFrame(String name) {
        //TODO: khoi tao
        super(name);
        this.setLayout(null);
        //TODO: khoi tao cac bien giao dien
        Dic = new kWord();
        dicva = new kWord(); dicva.setPath("V_E.txt"); dicva.readData();
        dicav = new kWord(); dicav.setPath("E_V.txt"); dicav.readData();


        txt_serch = new JTextField();
        btn_removeWord = new JButton("");
        btn_removeWord.setIcon(new ImageIcon(iconRemove));
        btn_addWord = new JButton(new ImageIcon(""));
        btn_addWord.setIcon(new ImageIcon(iconAdd));
        txt_list = new JList();
        txt_pane = new JLabel();
        scroll = new JScrollPane(txt_list);
        scrollLabel = new JScrollPane(txt_pane);
        btn_modifi = new JButton("");
        btn_modifi.setIcon(new ImageIcon(iconModify));
        model = new DefaultListModel<>();
        VietAnh = new JButton("");
        VietAnh.setIcon(new ImageIcon(iconVA));
        AnhViet = new JButton("");
        AnhViet.setIcon(new ImageIcon(iconAV));
        onlineS = new JButton("ONLINE");
        onlineS.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        modifiPanel = new JPanel();
        modifiArea = new JTextArea(" Chang def...");
        modifiArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

        modifibtn = new JButton("MODIFY");
        modifibtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        modifiCancel = new JButton("CANCEL");
        modifiCancel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        // TODO: cac bien addword
        btnadd = new JButton("ADD");
        btnadd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnCancel = new JButton("CANCEL");
        btnCancel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        combo = new JComboBox<>();
        combo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        addTextfile = new JTextField(" word");
        addTextfile.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        addTextArea = new JTextArea(" def");
        addTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        addTextArea.setLineWrap(true);
        addpanel = new JPanel();

        addpanel.setLayout(new BorderLayout());
        modifiPanel.setLayout(new BorderLayout());

        btn_speech = new JButton("");
        btn_speech.setIcon(new ImageIcon(iconSpeaker));
        //TODO: cai dat cac bien giao dien
        txt_serch.setBounds(50, 10, 260, 40);
        txt_serch.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        VietAnh.setBounds(320, 10, 150, 40);
        AnhViet.setBounds(480, 10, 150, 40);
        scroll.setBounds(110, 70, 200, 450);
        scrollLabel.setBounds(320, 70, 430, 450);
        btn_modifi.setBounds(50, 70, 50, 50);
        btn_removeWord.setBounds(50, 130, 50, 50);
        btn_addWord.setBounds(50, 190, 50, 50);
        addpanel.setBounds(320, 70, 400, 200);
        modifiPanel.setBounds(320, 70, 400, 200);
        onlineS.setBounds(640, 10, 110, 40);
        btn_speech.setBounds(50, 250, 50, 50);
        txt_list.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        txt_pane.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        //TODO: add word
        addpanel.add(addTextfile, BorderLayout.NORTH);
        addpanel.add(addTextArea, BorderLayout.CENTER);
        addpanel.add(btnCancel, BorderLayout.WEST);
        addpanel.add(combo, BorderLayout.SOUTH);
        addpanel.add(btnadd, BorderLayout.EAST);
        combo.addItem("Vietnamese - English");
        combo.addItem("English - Vietnamese");

        //TODO: modifi word
        modifiPanel.add(modifiArea, BorderLayout.CENTER);
        modifiPanel.add(modifibtn, BorderLayout.NORTH);
        modifiPanel.add(modifiCancel, BorderLayout.SOUTH);
        //TODO: add su kien
        addpanel.setBackground(Color.blue);
        AnhViet.addActionListener(this);
        VietAnh.addActionListener(this);
        txt_list.addListSelectionListener(this);
        txt_serch.getDocument().addDocumentListener(this);
        btn_addWord.addActionListener(this);
        btnadd.addActionListener(this);
        btn_removeWord.addActionListener(this);
        btn_modifi.addActionListener(this);
        onlineS.addActionListener(this);
        btn_modifi.addActionListener(this);
        modifibtn.addActionListener(this);
        btn_speech.addActionListener(this);
        btnCancel.addActionListener(this);
        modifiCancel.addActionListener(this);
        addList("E_V.txt");
        //TODO: test
        modifiPanel.setBackground(Color.blue);

        //TODO: add tat ca vao frame chinh
        this.add(txt_serch);
        this.add(scroll);
        this.add(scrollLabel);
        this.add(btn_modifi);
        this.add(btn_removeWord);
        this.add(btn_addWord);
        this.add(VietAnh);
        this.add(AnhViet);
        this.add(addpanel);
        this.add(onlineS);
        this.add(modifiPanel);
        this.add(btn_speech);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == AnhViet){
            addList("E_V.txt");
        }
        if (e.getSource() == VietAnh){
            addList("V_E.txt");
        }
        if (e.getSource() == btn_addWord){
            scrollLabel.setVisible(false);
            modifiPanel.setVisible(false);
            addpanel.setVisible(true);
        }
        if (e.getSource() == btnadd && combo.getSelectedItem().toString() == "ANH->VIET"){
            dicav.addWord(addTextfile.getText(), addTextArea.getText());
            System.out.println("hello");
            scrollLabel.setVisible(true);
            addpanel.setVisible(false);
            modifiPanel.setVisible(false);
        }
        if (e.getSource() == btnadd && combo.getSelectedItem().toString() == "VIET->ANH"){
            dicva.addWord(addTextfile.getText(), addTextArea.getText());
            System.out.println("hi");
            scrollLabel.setVisible(true);
            modifiPanel.setVisible(false);
            addpanel.setVisible(false);

        }
        if (e.getSource() == onlineS){
            String s = null;
            try {
                s = Translate.execute(txt_serch.getText(), Language.ENGLISH, Language.VIETNAM);
                txt_pane.setText( "  "+txt_serch.getText() + ":  "+ s);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            System.out.println(s);

        }
        if (e.getSource() == btn_modifi){
            scrollLabel.setVisible(false);
            addpanel.setVisible(false);
            modifiPanel.setVisible(true);
        }
        if (e.getSource() == modifibtn){
            if (Dic == dicav){
                dicav.modifiWord(model.get(txt_list.getAnchorSelectionIndex()), modifiArea.getText());
            }
            if (Dic == dicva){
                dicva.modifiWord(model.get(txt_list.getAnchorSelectionIndex()), modifiArea.getText());
            }
            modifiPanel.setVisible(false);
            addpanel.setVisible(false);
            scrollLabel.setVisible(true);
        }
        if (e.getSource() == btn_removeWord){
            addpanel.setVisible(false);
            modifiPanel.setVisible(false);
            scrollLabel.setVisible(true);
            if (Dic == dicav){
                dicav.removeWord(model.get(txt_list.getAnchorSelectionIndex()));
                model.remove(txt_list.getAnchorSelectionIndex());
            }
            if (Dic == dicva){
                dicva.removeWord(model.get(txt_list.getAnchorSelectionIndex()));
                model.remove(txt_list.getAnchorSelectionIndex());
            }
        }
        if (e.getSource() == btn_speech){
            TextToSpeech.speak(model.get(txt_list.getAnchorSelectionIndex()));
        }
        if(e.getSource() == btnCancel){
            scrollLabel.setVisible(true);
            modifiPanel.setVisible(false);
            addpanel.setVisible(false);
        }
        if (e.getSource() == modifiCancel){
            modifiPanel.setVisible(false);
            addpanel.setVisible(false);
            scrollLabel.setVisible(true);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        txt_pane.setText(Dic.getData().get(model.get(txt_list.getAnchorSelectionIndex())));
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String tempS = txt_serch.getText();
        int a = Dic.BinarySearch(Dic.getKeys(), tempS);
        txt_list.setSelectedValue(model.get(a), true);

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String tempS = txt_serch.getText();
        int a = Dic.BinarySearch(Dic.getKeys(), tempS);
        txt_list.setSelectedValue(model.get(a), true);

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String tempS = txt_serch.getText();
        int a = Dic.BinarySearch(Dic.getKeys(), tempS);
        txt_list.setSelectedValue(model.get(a), true);

    }
}
