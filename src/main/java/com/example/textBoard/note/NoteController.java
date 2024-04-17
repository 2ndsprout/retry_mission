package com.example.textBoard.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/main")
    public String root (Model model) {
        List<Note> noteList = this.noteService.getList();
        if (noteList.isEmpty()) {
            this.noteService.create();
            return "redirect:/";
        }
        model.addAttribute("noteList", noteList);
        model.addAttribute("target", noteList.getFirst());

        return "main";
    }
    @PostMapping("/create")
    public String create() {
        this.noteService.create();
        return "redirect:/note/main";
    }
    @GetMapping("/detail/{id}")
    public String detail (@PathVariable("id")Long id, Model model) {
        Note note = this.noteService.getNote(id);
        model.addAttribute("note", note);
        model.addAttribute("noteList",this.noteService.getList());

        return "main";
    }
}
