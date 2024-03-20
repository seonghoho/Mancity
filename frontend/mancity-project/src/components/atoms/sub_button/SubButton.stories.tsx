import SubButton from "@/components/atoms/sub_button/SubButton";
import type { Meta, StoryObj } from "@storybook/react";

const meta: Meta<typeof SubButton> = {
  title: "Components/atoms/Button/SubButton",
  tags: ["autodocs"],
  component: SubButton,
};

export default meta;

type Story = StoryObj<typeof SubButton>;

export const Default: Story = {
  args: {
    label: "Sub Button",
    hover: true,
  },
};

export const NoHover: Story = {
  args: {
    label: "Sub Button",
    hover: false,
  },
};