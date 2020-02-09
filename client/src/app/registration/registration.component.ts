import {Component, OnInit} from '@angular/core';
import {RegistrationService} from '../service/registration.service';
import {FormResponse} from '../types/formResponse';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  private registrationForm: FormGroup;
  private formResponse = null;
  private formFields = [];
  private processInstance = '';


  constructor(private registrationService: RegistrationService) {
  }

  ngOnInit() {
    this.startRegistrationProcess();
  }

  startRegistrationProcess() {
    this.registrationService.startRegistrationProcess().subscribe(
      (res: FormResponse) => {
        console.log(res);
        this.formResponse = res;
        this.formFields = res.formFields;
        /* console.log(this.formResponse);
         console.log(this.formFields);*/
        this.formFields.forEach((formField) => {
          this.registrationForm.addControl(formField.id, new FormControl('', this.extractValidators(formField)));
        });
      },
      err => {
        console.log('An error occurred.');
      });
  }

  submitRegistrationForm() {
    this.registrationService.submitRegistrationForm(null, null).subscribe(
      (val) => {
        console.log('POST call successful value returned in body', val);
      },
      response => {
        console.log('POST call in error', response);
      },
      () => {
        console.log('The POST observable is now completed.');
      });
  }

  extractValidators(field) {
    const validators = [];
    if (field.validationConstraints) {
      field.validationConstraints.forEach(constraint => {
        if (field.type.name === 'email') {
          validators.push(Validators.email);
        }
        if (constraint.name === 'required') {
          validators.push(Validators.required);
        }
        if (constraint.name === 'minlength') {
          validators.push(Validators.minLength(constraint.configuration));
        }
        if (constraint.name === 'maxlength') {
          validators.push(Validators.maxLength(constraint.configuration));
        }
      });
    }
    return validators;
  }

}
