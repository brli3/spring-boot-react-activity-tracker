import { Card, Button, Form, Input, Select, InputNumber, message } from "antd";
import { ArrowLeftOutlined } from "@ant-design/icons";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import {
  reqAddOrUpdateMeal,
  reqVisibleMealCategories,
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

function EditMeal() {
  const [options, setOptions] = useState([]);
  const navigate = useNavigate();
  const { state } = useLocation();
  const { meal } = state || {};

  const fetchOptions = async () => {
    const categories = await reqVisibleMealCategories();
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
      <span>{meal ? "Update" : "Add"} Meal</span>
    </span>
  );

  const onFinish = async (values) => {
    console.log("Collected form values: ", values);
    const newMeal = { ...values };
    if (meal) newMeal.id = meal.id;
    console.log(newMeal);
    await reqAddOrUpdateMeal({ ...newMeal });
    message.success(`${meal ? "Update" : "Add"} meal successful`);
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
          initialValue={meal?.name}
          rules={[{ required: true }]}
        >
          <Input placeholder="Meal name" />
        </Item>
        <Item
          name="categoryId"
          label="Category"
          initialValue={meal?.categoryId}
          rules={[{ required: true }]}
        >
          <Select placeholder="Select a category">
            {options.map((option) => (
              <Option key={option.id} value={option.id}>
                {option.name}
              </Option>
            ))}
          </Select>
        </Item>
        <Item
          name="calPerServing"
          label="Calories per serving"
          rules={[{ required: true, min: 0.0, max: 10000.0 }]}
        >
          {/* Make sure only one tag within Item to get input value */}
          <div>
            <InputNumber
              defaultValue={meal?.calPerServing}
              min={0}
              max={10000}
              precision="1"
              placeholder="300"
            />
          </div>
        </Item>
        <Item name="desc" label="Description" initialValue={meal?.desc}>
          <TextArea rows={3} placeholder="Describe the meal" />
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

export default EditMeal;
