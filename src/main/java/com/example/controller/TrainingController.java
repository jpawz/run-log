package com.example.controller;

import java.util.List;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.TrainingDTO;
import com.example.mapper.Mapper;
import com.example.repository.TrainingRepository;

@Controller
public class TrainingController {

    private static final String REDIRECT_TO_HOMEPAGE = "redirect:/index";

    @Autowired
    private TrainingRepository repository;

    @Autowired
    private Mapper mapper;

    /**
     * Opens the home page.
     * 
     * @param model - holds list of {@link TrainingDTO}.
     * @return home page address
     */
    @GetMapping(value = { "/", "/index" })
    public String index(Model model) {
	List<TrainingDTO> trainings = StreamSupport.stream(repository.findAll().spliterator(), false)
		.map(mapper::toDto)
		.toList();
	model.addAttribute("trainings", trainings);
	return "index";
    }

    /**
     * Opens the Add New Training page.
     * 
     * @param trainingdto - object that will be created.
     * @param model       - stores the trainingdto object.
     * @return
     */
    @GetMapping("/add")
    public String addTrainingForm(TrainingDTO trainingdto, Model model) {
	model.addAttribute("trainingDTO", trainingdto);
	return "add";
    }

    /**
     * Submits new {@link TrainingDTO} for storage as {@link Training} id database.
     * 
     * @param trainingDTO   - object to store.
     * @param bindingResult - holds possible form errors.
     * @param model         - stores the trainingdto object.
     * @return
     */
    @PostMapping("/add")
    public String addNewTraining(@Valid TrainingDTO trainingDTO, BindingResult bindingResult, Model model) {
	if (bindingResult.hasErrors()) {
	    model.addAttribute("trainingDTO", trainingDTO);
	    return "add";
	}

	repository.save(mapper.toTraining(trainingDTO));
	return REDIRECT_TO_HOMEPAGE;
    }

    /**
     * Open page for editing existing {@link Training}, mapped as
     * {@link TrainingDTO}
     * 
     * @param id    - ID of the training
     * @param model - stores the trainingdto object.
     * @return
     */
    @GetMapping("/edit/{id}")
    public String updateTrainingForm(@PathVariable("id") int id, Model model) {
	TrainingDTO trainingdto = mapper.toDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
	model.addAttribute("trainingDTO", trainingdto);
	return "edit";
    }

    /**
     * Submits changed {@link TrainingDTO} for storage as {@link Training} id
     * database.
     * 
     * @param id            - ID of training
     * @param trainingDTO   - DTO of the training
     * @param bindingResult - holds possible form errors.
     * @param model         - stores the trainingdto object.
     * @return
     */
    @PostMapping("/update/{id}")
    public String updateTraining(@PathVariable("id") int id, @Valid TrainingDTO trainingDTO,
	    BindingResult bindingResult, Model model) {
	if (bindingResult.hasErrors()) {
	    model.addAttribute("trainingDTO", trainingDTO);
	    return "edit";
	}

	repository.save(mapper.toTraining(trainingDTO));
	return REDIRECT_TO_HOMEPAGE;
    }

    /**
     * Deletes training by ID.
     * 
     * @param id - ID of training.
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteTraining(@PathVariable("id") int id) {
	repository.deleteById(id);
	return REDIRECT_TO_HOMEPAGE;
    }
}
