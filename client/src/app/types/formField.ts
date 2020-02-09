import {ValidationConstraint} from './validationConstraint';

export interface FormField {
  id: string;
  label: string;
  type: any;
  value: any;
  validationConstraints: Array<ValidationConstraint>;
}
