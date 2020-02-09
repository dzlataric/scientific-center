import {FormField} from './formField';

export interface FormResponse {
  taskId: string;
  processInstanceId: string;
  formFields: Array<FormField>;
}
