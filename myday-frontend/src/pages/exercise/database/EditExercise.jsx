import { Card, Button, Form, Input, Select, InputNumber, message } from "antd";
import { ArrowLeftOutlined } from "@ant-design/icons";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import {
  reqAddOrUpdateExercise,
  reqVisibleExerciseCategories,
} from "../../../api";

const { TextArea } = Input;
const { Option } = Select;
const { Item } = Form;

const layout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 16,
  },
};

function EditExercise() {
  const [options, setOptions] = useState([]);
  const navigate = useNavigate();
  const { state } = useLocation();
  const { exercise } = state || {};

  const fetchOptions = async () => {
    const categories = await reqVisibleExerciseCategories();
    setOptions(categories);
  };

  useEffect(() => {
    fetchOptions();
  }, []);

  const title = (
    <span>
      <Button type="link" onClick={() => navigate(-1)}>
        <ArrowLeftOutlined />
      </Button>
      <span>{exercise ? "Update" : "Add"} Exercise</span>
    </span>
  );

  const onFinish = async (values) => {
    console.log("Collected form values: ", values);
    const newExercise = { ...values };
    if (exercise) newExercise.id = exercise.id;
    console.log(newExercise);
    await reqAddOrUpdateExercise({ ...newExercise });
    message.success(`${exercise ? "Update" : "Add"} exercise successful`);
    navigate(-1);
  };

  return (
    <Card title={title}>
      <Form
        {...layout}
        onFinish={onFinish}
        style={{ maxWidth: 500, margin: "auto" }}
      >
        <Item
          name="name"
          label="Name"
          initialValue={exercise?.name}
          rules={[{ required: true }]}
        >
          <Input placeholder="Exercise name" />
        </Item>
        <Item
          name="categoryId"
          label="Category"
          initialValue={exercise?.categoryId}
          rules={[{ required: true }]}
        >
          <Select placeholder="Select a category">
            <Option value="">N/A</Option>
            {options.map((option) => (
              <Option key={option.id} value={option.id}>
                {option.name}
              </Option>
            ))}
          </Select>
        </Item>
        <Item
          name="met"
          label="METs"
          rules={[{ required: true, min: 0.0, max: 50.0 }]}
        >
          {/* Make sure only one tag within Item to get input value */}
          <div>
            <InputNumber
              defaultValue={exercise?.met}
              min={0}
              max={50}
              precision="1"
              placeholder="3.5"
            />
            <span style={{ fontSize: 14, fontStyle: "italic", margin: 9 }}>
              <a
                href="https://en.wikipedia.org/wiki/Metabolic_equivalent_of_task"
                target="_blank"
                rel="noreferrer"
              >
                metabolic equivalent of task
              </a>
            </span>
          </div>
        </Item>
        <Item name="desc" label="Description" initialValue={exercise?.desc}>
          <TextArea rows={3} placeholder="Describe the exercise" />
        </Item>
        <Item wrapperCol={{ ...layout.wrapperCol, offset: 6 }}>
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Item>
      </Form>
    </Card>
  );
}

export default EditExercise;
