import { Card, Input, DatePicker, Form } from "antd";

const { Item } = Form;
const { TextArea } = Input;

function SelectDate({ setComment, setCreatedOn }) {
  const [form] = Form.useForm();

  const onChangeDate = (_, dateString) => {
    setCreatedOn(dateString);
  };

  const onChangeComment = (e) => {
    setComment(e.target.value);
  };

  return (
    <Card>
      <Form
        layout="vertical"
        form={form}
        style={{ maxWidth: 400, margin: "auto" }}
      >
        <Item label="Select a date for the activity" required>
          <DatePicker size="large" onChange={onChangeDate} />
        </Item>
        <Item name="comments" label="Comments">
          <TextArea
            rows={3}
            onChange={onChangeComment}
            placeholder="Describe the activity (optional)"
          />
        </Item>
      </Form>
    </Card>
  );
}

export default SelectDate;
